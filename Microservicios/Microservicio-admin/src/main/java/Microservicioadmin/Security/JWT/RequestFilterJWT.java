package Microservicioadmin.Security.JWT;

import Microservicioadmin.Security.Services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RequestFilterJWT extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestFilterJWT.class);
    public static final String BEARER = "Bearer ";

    @Autowired
    private TokenUtilJWT tokenUtilJWT;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getTokenFromRequest(request);
            if(token != null && tokenUtilJWT.validateToken(token)){
                String username = tokenUtilJWT.getUsernameWithToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }catch (Exception e){
            logger.error("No se pudo autenticar el usuario",e.getMessage());
        }
        filterChain.doFilter(request,response);
    }

    private String getTokenFromRequest(HttpServletRequest r){
        String header = r.getHeader("Authorization");
        if(StringUtils.hasText(header)&&header.startsWith(BEARER)){
            return header.substring(BEARER.length());
        }
        return null;
    }
}
