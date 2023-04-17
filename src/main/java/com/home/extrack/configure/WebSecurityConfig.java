package com.home.extrack.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${auth-header-name}")
    private String requestHeader;
    @Value("${auth-token}")
    private String requestValue;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        APIKeyAuthFilter filter = new APIKeyAuthFilter(requestHeader);
        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();
            if (!requestValue.equals(principal)) {
                throw new BadCredentialsException("Key was not found or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });
        httpSecurity.
                antMatcher("/**").
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().authorizeRequests().antMatchers("/home/**").permitAll().
                and().cors().
                and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.setMaxAge(1200L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
