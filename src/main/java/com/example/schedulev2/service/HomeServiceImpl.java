package com.example.schedulev2.service;

import com.example.schedulev2.dto.member.SignInResponseDto;
import com.example.schedulev2.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeRepository homeRepository;

    @Override
    public SignInResponseDto findById(Long memberId) {
        return null;
    }
}
