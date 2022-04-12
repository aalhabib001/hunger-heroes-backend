package com.hungerheroes.backend.utils;

import com.hungerheroes.backend.jwt.security.services.UserPrinciple;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthUtils {

    public static String getUserName() {
        return ((UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static UserPrinciple getAuthInfo() {
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
