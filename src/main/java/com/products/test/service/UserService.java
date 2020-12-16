package com.products.test.service;

import com.products.test.model.User;
import com.products.test.model.dto.UserRequestDto;
import com.products.test.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    User add(UserRequestDto userRequestDto);

    List<UserResponseDto> getAll();

    User get(Long id);

    void update(Long id, UserRequestDto userRequestDto);

    void delete(Long id);

    User addMoney(Long id, Integer amount);
}
