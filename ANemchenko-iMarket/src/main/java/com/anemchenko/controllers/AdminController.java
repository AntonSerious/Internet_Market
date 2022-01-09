package com.anemchenko.controllers;


import com.anemchenko.configs.JwtTokenUtil;
import com.anemchenko.dto.JwtRequest;
import com.anemchenko.dto.JwtResponse;
import com.anemchenko.exceptions.MarketError;
import com.anemchenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    @GetMapping("/check")
    public void roleCheck(){

    }

}
