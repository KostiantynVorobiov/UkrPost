package com.products.test.model.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String password;
    private int moneyAccount;
}
