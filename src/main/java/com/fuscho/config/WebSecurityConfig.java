package com.fuscho.config;

import com.fuscho.service.AuthentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Created by chocho on 07/10/15.
 **/
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthentificationService authentificationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/img/**", "/css/**", "/js/**", "/components/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .defaultSuccessUrl("/")
                    .loginPage("/auth")
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .and()
                    .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authentificationService);
    }

}