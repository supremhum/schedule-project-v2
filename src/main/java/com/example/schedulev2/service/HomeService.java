package com.example.schedulev2.service;

import com.example.schedulev2.dto.member.SignInRequestDto;
import com.example.schedulev2.dto.member.SignInResponseDto;

public interface HomeService {
    SignInResponseDto findById(Long memberId);
    SignInResponseDto signIn(SignInRequestDto requestDto);
}
