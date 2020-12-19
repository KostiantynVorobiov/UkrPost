package com.products.test.service.impl;

import com.products.test.model.ShoppingCart;
import com.products.test.model.User;
import com.products.test.model.dto.UserRequestDto;
import com.products.test.model.dto.UserResponseDto;
import com.products.test.repository.ShoppingCartRepository;
import com.products.test.repository.UserRepository;
import com.products.test.service.UserService;
import com.products.test.service.mapper.UserMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public User add(UserRequestDto userRequestDto) {
        User user = userMapper.mapToModel(userRequestDto);
        User newUser = userRepository.save(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(newUser);
        shoppingCartRepository.save(shoppingCart);
        return newUser;
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
    public void update(Long id, User user) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find user by id: " + id));
        user.setId(user.getId());
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User addMoney(Long id, Double amount) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find user by id: " + id));
        user.setMoneyAccount(user.getMoneyAccount() + amount);
        UserResponseDto userResponseDto = userMapper.mapToResponseDto(user);
        return userRepository.save(user);
    }

}
