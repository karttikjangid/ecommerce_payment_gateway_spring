package com.example.ecommerce_payment_gateway.controller;

import com.example.ecommerce_payment_gateway.dto.UserRequestDto;
import com.example.ecommerce_payment_gateway.dto.UserResponseDto;
import com.example.ecommerce_payment_gateway.model.User;
import com.example.ecommerce_payment_gateway.service.UserService;
import com.example.ecommerce_payment_gateway.util.MapperUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());

        return MapperUtil.toUserResponse(userService.createUser(user));
    }
}