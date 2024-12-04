package com.example.testtask1.model.dto;

import lombok.Data;

@Data
public class AuthenticationRequestModel {
    private String email;
    private String password;
}
