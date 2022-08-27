package com.anemchenko.api.dtos;

public class ProfileDto {
    private String username;
//    private String firstName;
//    private String lastName;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfileDto() {
    }

    public ProfileDto(String username, String email) {
        this.username = username;
//        this.firstName = firstName;
//        this.lastName = lastName;
        this.email = email;
    }
}