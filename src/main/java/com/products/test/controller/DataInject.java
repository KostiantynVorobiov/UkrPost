package com.products.test.controller;

import com.products.test.model.dto.UserRequestDto;
import com.products.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInject {
    private final UserService userService;

    @Autowired
    public DataInject(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        System.out.println("Added users");
      //  saveUsers();
    }

    private void saveUsers() {
        UserRequestDto bob = new UserRequestDto();
        bob.setEmail("bob@i.ua");
        bob.setPassword("11111");
        bob.setMoneyAccount(150);
        userService.add(bob);
        UserRequestDto alice = new UserRequestDto();
        alice.setEmail("alice@i.ua");
        alice.setPassword("22222");
        alice.setMoneyAccount(250);
        userService.add(alice);
        UserRequestDto tom = new UserRequestDto();
        tom.setEmail("tom@i.ua");
        tom.setPassword("33333");
        tom.setMoneyAccount(350);
        userService.add(tom);
    }
}
