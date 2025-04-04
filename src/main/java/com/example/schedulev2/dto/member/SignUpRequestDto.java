package com.example.schedulev2.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class SignUpRequestDto {

    @Email
    @NotBlank
    private final String email;
    @NotNull
    private final String password;
    @NotBlank
    private final String name;

    public SignUpRequestDto(String email,String password,String name) {
        this.email=email;
        this.password=password;
        this.name=name;
    }
}
