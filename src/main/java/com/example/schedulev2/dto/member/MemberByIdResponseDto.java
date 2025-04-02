package com.example.schedulev2.dto.member;

import com.example.schedulev2.entity.Member;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberByIdResponseDto {
    private final String email;
    private final String name;
    private final LocalDate createdAt;
    private final LocalDate modifiedAt;

    public MemberByIdResponseDto(String email, String name, LocalDateTime createdAt,LocalDateTime modifiedAt) {
        this.email = email;
        this.name = name;
        this.createdAt = createdAt.toLocalDate();
        this.modifiedAt = modifiedAt.toLocalDate();
    }

    public MemberByIdResponseDto(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
        this.createdAt = member.getCreatedAt().toLocalDate();
        this.modifiedAt = member.getModifiedAt().toLocalDate();
    }

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(member.getEmail(),member.getName());
    }
}
