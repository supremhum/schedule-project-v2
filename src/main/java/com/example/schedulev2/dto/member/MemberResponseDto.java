package com.example.schedulev2.dto.member;

import com.example.schedulev2.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final String email;
    private final String name;

    public MemberResponseDto(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public MemberResponseDto(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
    }

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(member.getEmail(),member.getName());
    }
}
