package com.anemchenko.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    public ProfileDto(String username, String email){
        this.username = username;
        this.email = email;
    }
}