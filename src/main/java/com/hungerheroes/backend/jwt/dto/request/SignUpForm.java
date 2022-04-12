package com.hungerheroes.backend.jwt.dto.request;

import com.hungerheroes.backend.dto.request.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpForm {

    private String phoneNo;

    private String password;

    private String name;

    private String userName;

    private UserType userType;

}