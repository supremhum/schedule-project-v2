package com.example.schedulev2.dto.member;

import lombok.Getter;

@Getter
public class PasswordUpdateRequestDto {
    private final String oldPassword;
    private final String newPassword;

    public PasswordUpdateRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}

