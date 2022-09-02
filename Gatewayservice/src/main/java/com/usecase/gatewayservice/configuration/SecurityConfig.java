package com.usecase.gatewayservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // Enable Spring Security’s web
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // To configure method-level security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .and()
                .authorizeRequests(authz -> authz.mvcMatchers("/")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2Login()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}
