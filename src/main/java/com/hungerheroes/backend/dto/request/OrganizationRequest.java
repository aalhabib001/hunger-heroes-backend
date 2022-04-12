package com.hungerheroes.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequest {

    private String orgName;

    private String orgDescription;

    private String orgWebsite;

    private String orgPhone;

    private String orgAddress;

    private String orgImageLink;
}
