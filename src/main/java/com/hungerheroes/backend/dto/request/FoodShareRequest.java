package com.hungerheroes.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodShareRequest {

    private String title;

    private String address;

    private String phoneNo;
}
