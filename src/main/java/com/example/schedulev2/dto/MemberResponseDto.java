package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final String email;
    private final String name;

    public MemberResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
