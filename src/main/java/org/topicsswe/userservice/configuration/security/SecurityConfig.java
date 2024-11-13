package org.topicsswe.userservice.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;

// jwt based auth: https://medium.com/@thecodebean/implementing-jwt-authentication-in-a-spring-boot-application-5a7a94d785d1
// cognito: https://dev.to/daviidy/api-security-how-to-implement-authentication-and-authorization-with-aws-cognito-in-spring-boot-4713
// Roles: https://howtodoinjava.com/spring-security/spring-boot-role-based-authentication-with-aws-cognito/
@Configuration
public class SecurityConfig  {

    @Value("${cognito.tokenSigningKey}")
    private String tokenSigningKey;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
//                .cors(cors -> cors.) //TODO enable cors?
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/register").permitAll()
//
//                        // TODO check if we need this or if we could just add a database like userId - Role
//                        .requestMatchers("/admin/**")
//                        .hasRole("ADMIN")
//
//                        .anyRequest().authenticated()
                                .anyRequest().permitAll()

                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .decoder(jwtDecoder())
                                .jwtAuthenticationConverter(authenticationConverter())
                        )
                );
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(tokenSigningKey).build();
    }

//    Converter: https://www.baeldung.com/spring-security-map-authorities-jwt
    @Bean
    public JwtAuthenticationConverter authenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(this::mapAuthorities);
        return converter;
    }

    private Collection<GrantedAuthority> mapAuthorities(Jwt jwt) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + jwt.getClaim("custom:role")));
    }
}
