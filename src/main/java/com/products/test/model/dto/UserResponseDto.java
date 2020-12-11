package com.products.test.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private String password;
    private int moneyAccount;
}
