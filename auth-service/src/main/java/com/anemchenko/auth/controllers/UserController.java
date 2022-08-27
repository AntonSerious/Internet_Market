package com.anemchenko.auth.controllers;

import com.anemchenko.api.dtos.ProfileDto;
import com.anemchenko.api.exceptions.ResourceNotFoundException;
import com.anemchenko.auth.model.User;
import com.anemchenko.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/com/anemchenko/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ProfileDto aboutCurrentUser(@RequestHeader String username) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя. Имя пользователя: " + username));
        return new ProfileDto(user.getUsername(), user.getEmail());
    }
}
