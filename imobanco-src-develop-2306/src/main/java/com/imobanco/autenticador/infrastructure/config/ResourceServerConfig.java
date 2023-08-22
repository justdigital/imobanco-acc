package com.imobanco.autenticador.infrastructure.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    private final KeycloakJwtAuthenticationConverter keycloakJwtAuthenticationConverter;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
        .authorizeRequests(authz -> authz.antMatchers("/cadastropf/**").authenticated())
        .oauth2ResourceServer()
        .jwt().jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);

        http
                .authorizeRequests(authz -> authz.antMatchers("/documento/**").authenticated())
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);

        http
                .authorizeRequests(authz -> authz.antMatchers("/tipodocumento/**").authenticated())
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);

        http
                .authorizeRequests(authz -> authz.antMatchers("/tipocomprovante/**").authenticated())
                .oauth2ResourceServer()
                .jwt().jwtAuthenticationConverter(keycloakJwtAuthenticationConverter);

       }
}
