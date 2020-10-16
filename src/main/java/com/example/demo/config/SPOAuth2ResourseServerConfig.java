package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class SPOAuth2ResourseServerConfig extends WebSecurityConfigurerAdapter
{

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Override    
    protected void configure(HttpSecurity http) throws Exception
    {
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET)
                //.hasRole("sp-api-doc.read")
                .hasAuthority("SCOPE_sp-api-doc.read")
                .antMatchers(HttpMethod.POST)
                //.hasRole("sp-api-doc.write")
                .hasAuthority("SCOPE_sp-api-doc.write")
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt( jwt ->   jwt.decoder(jwtDecoder()));
        // @formatter:on
    }
    
    
    @Bean
    JwtDecoder jwtDecoder()
    {
        
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }
    
}