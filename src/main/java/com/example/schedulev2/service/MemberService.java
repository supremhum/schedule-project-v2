package com.example.schedulev2.service;

import com.example.schedulev2.dto.SignUpRequestDto;
import com.example.schedulev2.dto.SignUpResponseDto;

public interface MemberService {
    SignUpResponseDto signUp(SignUpRequestDto requestDto);
}
