package com.example.schedulev2.dto.member;

import lombok.Getter;

@Getter
public class SignInRequestDto {
    private final String email;
    private final String password;

    public SignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
