package com.products.test.service.mapper;

import com.products.test.model.User;
import com.products.test.model.dto.UserRequestDto;
import com.products.test.model.dto.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User mapToModel(UserRequestDto userRequestDto) {
        return User.builder()
                .email(userRequestDto.getEmail())
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .moneyAccount(userRequestDto.getMoneyAccount())
                .build();
    }

    public UserResponseDto mapToResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .moneyAccount(user.getMoneyAccount())
                .build();
    }
}
