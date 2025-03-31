package com.example.schedulev2.dto;

import lombok.Getter;


@Getter
public class SignUpRequestDto {

    private final String email;
    private final String password;
    private final String name;

    public SignUpRequestDto(String email,String password,String name) {
        this.email=email;
        this.password=password;
        this.name=name;
    }
}
