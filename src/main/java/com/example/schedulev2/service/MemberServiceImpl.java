package com.example.schedulev2.service;

import com.example.schedulev2.dto.MemberResponseDto;
import com.example.schedulev2.dto.SignUpRequestDto;
import com.example.schedulev2.dto.SignUpResponseDto;
import com.example.schedulev2.dto.UpdatePasswordRequestDto;
import com.example.schedulev2.entity.Member;
import com.example.schedulev2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        Member member = new Member(requestDto.getEmail(),requestDto.getPassword(),requestDto.getName());
        Member saveMember = memberRepository.save(member);
        return new SignUpResponseDto(saveMember.getId(), saveMember.getEmail(), saveMember.getName());
    }

    @Override
    public MemberResponseDto findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id : " + id);
        }
        Member member = optionalMember.get();
        return new MemberResponseDto(member.getEmail(), member.getName());
    }

    @Transactional
    @Override
    public void updatePassword(Long id, UpdatePasswordRequestDto passwordRequestDto) {
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if (!findMember.getPassword().equals(passwordRequestDto.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password does not match");
        }
        findMember.updatePassword(passwordRequestDto.getOldPassword(), passwordRequestDto.getNewPassword());


    }

}
