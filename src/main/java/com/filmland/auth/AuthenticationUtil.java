package com.filmland.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {

    public String getAuthenticatedUserEmail(){
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
