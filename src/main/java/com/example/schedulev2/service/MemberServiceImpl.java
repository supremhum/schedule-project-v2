package com.example.schedulev2.service;

import com.example.schedulev2.dto.SignUpRequestDto;
import com.example.schedulev2.dto.SignUpResponseDto;
import com.example.schedulev2.entity.Member;
import com.example.schedulev2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        Member member = new Member(requestDto.getEmail(),requestDto.getPassword(),requestDto.getName());
        Member saveMember = memberRepository.save(member);
        return new SignUpResponseDto(saveMember.getId(), saveMember.getEmail(), saveMember.getName());
    }
}
