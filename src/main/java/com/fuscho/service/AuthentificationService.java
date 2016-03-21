package com.fuscho.service;

import com.fuscho.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * Created by chocho on 07/10/15.
 **/
@Service
@Slf4j
public class AuthentificationService implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        // On récupère les données envoyés via le formulaire
        String email = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) authentication.getDetails();

        log.info("Try to connect {}", email);
        User user = new User(email);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public static User getAuthUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
