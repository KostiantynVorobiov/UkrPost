package com.products.test.model.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String firstName;
    private String lastName;
    private double moneyAccount;
}
