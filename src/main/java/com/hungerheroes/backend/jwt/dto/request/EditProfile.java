package com.hungerheroes.backend.jwt.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EditProfile {

    String phoneNo;

    private String name;
    private String address;
    private String dateOfBirth;
    private String thana;
    private String district;

    private String email;

}
