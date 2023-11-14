package Microservicioadmin.Security.JWT;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenUtilJWT {
    private static final Logger log = LoggerFactory.getLogger(TokenUtilJWT.class);

    @Value("microservicios")
    private String jwtSecret;

    @Value("600000") //10 min
    private int jwtExpirationMs;

    private static final String AUTHORITIES_KEY = "auth";
    private JwtParser jwtParser;


    public String generateToken(Authentication auth){
        UserDetails principal = (UserDetails) auth.getPrincipal(); //dealles del usuario a autenticado
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUsernameWithToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException sE){
            log.error("Firma invalida", sE.getMessage());
        }catch (MalformedJwtException mE){
            log.error("Token con formato invalido", mE.getMessage());
        }catch (ExpiredJwtException eE){
            log.error("Token expirado", eE.getMessage());
        }catch (UnsupportedJwtException uE){
            log.error("Token con algoritmos no admitidos", uE.getMessage());
        }catch (IllegalArgumentException iE){
            log.error("Token nulo o vacio", iE.getMessage());
        }
        return false;
    }

    public Authentication getAuthentication(String token){

        Claims claims = jwtParser.parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays
                .stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .filter(auth -> !auth.trim().isEmpty())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }
}
