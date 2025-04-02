package com.example.schedulev2.dto.member;

import lombok.Getter;

@Getter
public class SignInResponseDto {

    private final String email;

    private final Long id;

    public SignInResponseDto(String email, Long id) {
        this.email = email;
        this.id = id;
    }

}
