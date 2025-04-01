package com.example.schedulev2.controller;


import com.example.schedulev2.dto.member.*;
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

//    // 이름과 이메일만 나오게 한다
//    @GetMapping("/members")
//    public ResponseEntity<List<MemberResponseDto>> findAll() {
//        List<MemberResponseDto> findAllList = memberService.findAll();
//        return new ResponseEntity<>(findAllList,HttpStatus.OK);
//    }

    // 이름과 이메일과 아이디로 검색할 수 있다. 없으면 findAll 이고 반환은 이름과 이메일만.
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> search(MemberSearchRequestDto dto) {
        List<MemberResponseDto> findAllList = memberService.search(dto);
        return new ResponseEntity<>(findAllList,HttpStatus.OK);
    }

    // 이름 이메일 생성일,수정일(yyyy-mm-dd) 나오게 한다.
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberByIdResponseDto> findById(@PathVariable Long id) {
        MemberByIdResponseDto findById = memberService.findById(id);
        return new ResponseEntity<>(findById,HttpStatus.OK);
    }



    @PatchMapping("/members/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody PasswordUpdateRequestDto passwordRequestDto
    ) {
        memberService.updatePassword(id,passwordRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
