package com.example.schedulev2.service;

import com.example.schedulev2.dto.member.*;
import jakarta.validation.Valid;

import java.util.List;

public interface MemberService {
    SignUpResponseDto signUp(SignUpRequestDto requestDto);
    MemberByIdResponseDto findById(Long id);

    void updatePassword(Long id, PasswordUpdateRequestDto passwordRequestDto);

//    List<MemberResponseDto> findAll();

    List<MemberResponseDto> search(MemberSearchRequestDto dto);

    MemberResponseDto updateById(Long id, MemberUpdateRequestDto requestDto);

    void delete(Long id,MemberDeleteRequestDto requestDto);

}
