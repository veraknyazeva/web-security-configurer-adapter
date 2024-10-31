package ru.netology.websecurityconfigureradapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import ru.netology.websecurityconfigureradapter.converter.KCRoleConverter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter(); //конвертер для настройки spring security
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KCRoleConverter()); //подключаем конвертер ролей

        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user/*").hasRole("user") //hasAnyRole() перечисляем все роли
                .requestMatchers("/admin/*").hasRole("admin") //hasAuthority("admin") тоже самое что и hasRole, только тогда надо писать префикс ROLE
                .anyRequest().authenticated());

        http.oauth2ResourceServer(oauth2 -> oauth2 // добавляем конвертер ролей из JWT в Authority (Role)
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }
}