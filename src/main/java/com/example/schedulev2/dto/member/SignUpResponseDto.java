package com.example.schedulev2.dto.member;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final Long memberId;
    private final String email;
    private final String name;

    public SignUpResponseDto(Long memberId,String email,String name) {
        this.memberId=memberId;
        this.email=email;
        this.name=name;

    }
}
