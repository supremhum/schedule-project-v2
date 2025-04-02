package com.example.schedulev2.dto.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {
    private final Long id;
    private final String name;
    @Email
    private final String email;
    @NotNull
    private final String password;

    public MemberUpdateRequestDto(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
