package com.example.schedulev2.controller;


import com.example.schedulev2.dto.member.MemberResponseDto;
import com.example.schedulev2.dto.member.SignUpRequestDto;
import com.example.schedulev2.dto.member.SignUpResponseDto;
import com.example.schedulev2.dto.member.UpdatePasswordRequestDto;
import com.example.schedulev2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 가입. 이메일, 비밀번호,
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(requestDto);

        return new ResponseEntity<>(signUpResponseDto,HttpStatus.CREATED);

    }

    // 이름과 이메일만 나오게 한다
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> findAll() {
        List<MemberResponseDto> findAllList = memberService.findAll();
        return new ResponseEntity<>(findAllList,HttpStatus.OK);
    }

    // 이름 이메일 생성일 수정일 나오게 한다.
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto findById = memberService.findById(id);
        return new ResponseEntity<>(findById,HttpStatus.OK);
    }



    @PatchMapping("/members/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto passwordRequestDto
    ) {
        memberService.updatePassword(id,passwordRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
