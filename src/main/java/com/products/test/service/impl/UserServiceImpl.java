package com.products.test.service.impl;

import com.products.test.model.User;
import com.products.test.model.dto.UserRequestDto;
import com.products.test.model.dto.UserResponseDto;
import com.products.test.repository.UserRepository;
import com.products.test.service.UserService;
import com.products.test.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User add(UserRequestDto userRequestDto) {
        return userRepository.save(userMapper.mapToModel(userRequestDto));
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream()
                .map(userMapper::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public User get(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find user by id: " + id));
        return user;
    }

    @Override
    public void update(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find user by id: " + id));
        User userFromDto = userMapper.mapToModel(userRequestDto);
        userFromDto.setId(user.getId());
        userRepository.save(userFromDto);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addMoney(Long id, Integer amount) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find user by id: " + id));
        user.setMoneyAccount(user.getMoneyAccount() + amount);
        UserResponseDto userResponseDto = userMapper.mapToResponseDto(user);
        return userRepository.save(user);
    }


}
