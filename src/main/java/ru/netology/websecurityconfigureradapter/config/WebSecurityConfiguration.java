//package ru.netology.websecurityconfigureradapter.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfiguration {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                .anyRequest()
//                .authenticated());
//        http.headers(Customizer.withDefaults());
//        http.sessionManagement(Customizer.withDefaults());
//        http.formLogin(Customizer.withDefaults());
//        http.anonymous(Customizer.withDefaults());
//        http.csrf(Customizer.withDefaults());
//        return http.build();
//    }
//
////    @Bean
////    public UserDetailsService inMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
////        User.UserBuilder users = User.builder()
////                .passwordEncoder(passwordEncoder::encode);
////        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
////        userDetailsManager.createUser(users.username("annaRead")
////                .password("read")
////                .roles("READ")
////                .build());
////        userDetailsManager.createUser(users.username("veraWrite")
////                .password("write")
////                .roles("WRITE")
////                .build());
////        userDetailsManager.createUser(users.username("ivanDelete")
////                .password("delete")
////                .roles("DELETE")
////                .build());
////        return userDetailsManager;
////    }
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
//}
