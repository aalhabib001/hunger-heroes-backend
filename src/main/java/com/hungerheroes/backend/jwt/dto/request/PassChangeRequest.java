package com.hungerheroes.backend.jwt.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class PassChangeRequest {

    String oldPass;

    String newPass;
}
