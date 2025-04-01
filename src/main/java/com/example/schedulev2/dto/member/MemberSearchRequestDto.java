package com.example.schedulev2.dto.member;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberSearchRequestDto {

    private final Long id;
    private final String name;
    private final String email;

    public MemberSearchRequestDto(Long id, String name, String email) {
        this.id=id;
        this.name = name;
        this.email = email;
    }
}
