package com.products.test.controller;

import com.products.test.model.User;
import com.products.test.model.dto.UserRequestDto;
import com.products.test.model.dto.UserResponseDto;
import com.products.test.service.UserService;
import com.products.test.service.mapper.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public User create(@RequestBody UserRequestDto userRequestDto) {
        return userService.add(userRequestDto);
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{id}/{summa}")
    public void addAmount(@PathVariable Long id, @PathVariable Double summa) {
        userService.addMoney(id, summa);
    }

    @GetMapping("/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        User user = userService.get(id);
        return userMapper.mapToResponseDto(user);
    }

}
