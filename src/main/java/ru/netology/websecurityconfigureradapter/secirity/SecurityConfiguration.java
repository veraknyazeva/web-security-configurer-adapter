package ru.netology.websecurityconfigureradapter.secirity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/getName").permitAll()
                .and()
                .authorizeRequests().antMatchers("/get-users").hasAuthority("get-users")
                .and()
                .authorizeRequests().antMatchers("/getSurname").hasAuthority("getSurname")
                .and()
                .authorizeRequests().antMatchers("/getAge").hasAuthority("getAge")
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Vera")
                .password(encoder().encode("password"))
                .authorities("get-users", "getAge", "getSurname")
                .and()
                .withUser("Ivan")
                .password(encoder().encode("password1"))
                .authorities("getSurname", "getAge");
    }
}
