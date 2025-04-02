package com.example.schedulev2.dto.member;

import lombok.Getter;

@Getter
public class MemberDeleteRequestDto {
    private final String password;

    public MemberDeleteRequestDto(String password) {
        this.password = password;
    }
}
