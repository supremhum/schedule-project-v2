package com.example.schedulev2.service;

import com.example.schedulev2.dto.member.MemberResponseDto;
import com.example.schedulev2.dto.member.SignUpRequestDto;
import com.example.schedulev2.dto.member.SignUpResponseDto;
import com.example.schedulev2.dto.member.UpdatePasswordRequestDto;

import java.util.List;

public interface MemberService {
    SignUpResponseDto signUp(SignUpRequestDto requestDto);
    MemberResponseDto findById(Long id);

    void updatePassword(Long id, UpdatePasswordRequestDto passwordRequestDto);

    List<MemberResponseDto> findAll();

}
