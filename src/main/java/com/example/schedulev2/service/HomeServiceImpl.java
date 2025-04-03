package com.example.schedulev2.service;

import com.example.schedulev2.dto.member.SignInResponseDto;
import com.example.schedulev2.entity.Member;
import com.example.schedulev2.repository.HomeRepository;
import com.example.schedulev2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeRepository homeRepository;

    @Override
    public SignInResponseDto findById(Long memberId) {
        Optional<Member> findMember = homeRepository.findById(memberId);
        if (findMember.isEmpty()) {
            return null;
        }

        return new SignInResponseDto(findMember.get().getEmail(),memberId);
    }
}
