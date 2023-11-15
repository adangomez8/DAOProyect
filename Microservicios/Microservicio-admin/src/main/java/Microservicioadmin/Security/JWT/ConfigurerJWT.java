package Microservicioadmin.Security.JWT;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class ConfigurerJWT extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenUtilJWT tokenProvider;
    
    public ConfigurerJWT(TokenUtilJWT tokenprovider) {
    	this.tokenProvider=tokenProvider; 
    }

    @Override
    public void configure( HttpSecurity http ) {
        RequestFilterJWT customFilter = new RequestFilterJWT(tokenProvider);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
