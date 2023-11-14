package Microservicioadmin.Security.JWT;

import Microservicioadmin.Security.Services.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RequestFilterJWT extends GenericFilterBean {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenUtilJWT tokenProvider;
    public static final String BEARER = "Bearer ";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = this.getTokenFromRequest(httpServletRequest);
        try {
            if(jwt!=null&&tokenProvider.validateToken(jwt)){
                Authentication authentication = this.tokenProvider.getAuthentication( jwt );
                SecurityContextHolder.getContext().setAuthentication( authentication );
            }
        }catch (ExpiredJwtException e){
            final var response = ((HttpServletResponse) servletResponse );
            response.setStatus( 498 );
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("--");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /*private static final Logger logger = LoggerFactory.getLogger(RequestFilterJWT.class);
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenUtilJWT tokenProvider;

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
    }*/

    private String getTokenFromRequest(HttpServletRequest r){
        String header = r.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(header)&&header.startsWith(BEARER)){
            return header.substring(BEARER.length());
        }
        return null;
    }
}
