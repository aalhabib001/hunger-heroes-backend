package com.hungerheroes.backend.jwt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

    private String userName;

    private String email;

    private String name;

    private String phoneNo;

    private String address;

    private String dateOfBirth;

    private String thana;

    private String district;

}
