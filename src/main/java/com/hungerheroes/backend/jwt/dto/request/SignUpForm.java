package com.hungerheroes.backend.jwt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpForm {

    private String phone;

    private String password;

    private String name;

    private String userName;

}