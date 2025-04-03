package com.example.schedulev2.dto.member;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignInRequestDto {

    @NotNull private final String email;
    @NotNull private final String password;

    public SignInRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
