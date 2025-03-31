package com.example.schedulev2.controller;


import com.example.schedulev2.dto.MemberResponseDto;
import com.example.schedulev2.dto.SignUpRequestDto;
import com.example.schedulev2.dto.SignUpResponseDto;
import com.example.schedulev2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(requestDto);

        return new ResponseEntity<>(signUpResponseDto,HttpStatus.CREATED);

    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto findById = memberService.findById(id);
        return new ResponseEntity<>(findById,HttpStatus.OK);
    }



}
