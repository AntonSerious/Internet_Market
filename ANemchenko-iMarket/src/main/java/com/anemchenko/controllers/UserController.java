package com.anemchenko.controllers;

import com.anemchenko.dto.ProfileDto;

import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.User;
import com.anemchenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ProfileDto aboutCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя. Имя пользователя: " + principal.getName()));
        return new ProfileDto(user.getUsername(), user.getEmail());
    }
}
