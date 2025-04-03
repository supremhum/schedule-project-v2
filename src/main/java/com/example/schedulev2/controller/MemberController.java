package com.example.schedulev2.controller;


import com.example.schedulev2.dto.member.*;
import com.example.schedulev2.service.MemberService;
import jakarta.validation.Valid;
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
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(requestDto);

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);

    }
    @PostMapping("/signip")
    public ResponseEntity<SignUpResponseDto> signIn(@Valid @RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(requestDto);

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);

    }

//    // 이름과 이메일만 나오게 한다
//    @GetMapping("/members")
//    public ResponseEntity<List<MemberResponseDto>> findAll() {
//        List<MemberResponseDto> findAllList = memberService.findAll();
//        return new ResponseEntity<>(findAllList,HttpStatus.OK);
//    }

    // 이름과 이메일과 아이디를 query param으로 받아 검색할 수 있다. 없으면 findAll 이고 반환은 이름과 이메일만.
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> search(MemberSearchRequestDto dto) {
        List<MemberResponseDto> findAllList = memberService.search(dto);
        return new ResponseEntity<>(findAllList, HttpStatus.OK);
    }

    // 이름 이메일 생성일,수정일(yyyy-mm-dd) 나오게 한다.
    @GetMapping("/members/{id}")
    public ResponseEntity<MemberByIdResponseDto> findById(@PathVariable Long id) {
        MemberByIdResponseDto findById = memberService.findById(id);
        return new ResponseEntity<>(findById, HttpStatus.OK);
    }

    // 이름과 이메일 바꾸는 것은 Put 매서드로 들어간다. 비밀번호가 맞아야 수정 가능하다.
    // 이메일은 unique 속성이라 DB 까지 들어가서야 확인 가능한 로직으로 생각함
    @PutMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> updateById(@PathVariable Long id, @Valid @RequestBody MemberUpdateRequestDto requestDto) {
        MemberResponseDto responseDto= memberService.updateById(id,requestDto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    // 비밀번호 바꾸는 것은 Patch 매서드로 들어간다. 이전비밀번호와 바꿀비밀번호를 받는다
    @PatchMapping("/members/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody PasswordUpdateRequestDto passwordRequestDto
    ) {
        memberService.updatePassword(id, passwordRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 삭제 매서드 비밀번호가 맞아야 삭제 가능하다
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,@RequestBody MemberDeleteRequestDto requestDto) {
        memberService.delete(id,requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
