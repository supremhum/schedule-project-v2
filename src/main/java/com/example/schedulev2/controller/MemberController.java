package com.example.schedulev2.controller;


import com.example.schedulev2.dto.member.*;
import com.example.schedulev2.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

    // 로그인 기능 만들기
    @PostMapping("/signin")
    public String signIn(
            @Valid @RequestBody SignInRequestDto requestDto,

            // 쿠키값 세팅에 필요.
            // 뇌피셜 HttpServlet이 요청을 감지했고 이를 통해 header랑 cookie를 확인했을거고
            // 이를 컨트롤러에게 응답의 형태로 전달해주는것이 http-servlet-response 일까나?
            HttpServletResponse response
    ) {
        // responseDto 를 반환받는데 로직은 request 의 이메일과 비밀번호로 짜는 것.
        // 그건 서비스단에서 알아서 생각
        // 그리고 조회가 되었으니 이메일과 memberId 값을 Dto에 담아 받았다
        SignInResponseDto signInResponseDto = memberService.signIn(requestDto);

        // 이메일과 비번이 안맞아서 signInResponseDto 가 null 이면
        // 로그인 페이지로 가야합니다
        if (signInResponseDto==null) {
            return "signInPage";
        }

        // signInResponseDto 있다면 정상적으로 이메일과 비밀번호를 친 것이니 쿠키를 생성해 줘야한다
        // 그런데 쿠키는 memberId 값으로 준다고 지금은 뇌피셜
        // 쿠키생성을 하고 "memberId" 에 바인딩은 문자열로 바꾼 memberId 값. HTTP쿠키는 문자열로만!
        Cookie cookie = new Cookie("memberId",String.valueOf(signInResponseDto.getId()));

        // 쿠키에 값 세팅(설정등. default 가 브라우저 종료시 삭제고 이는 따로 건드리지 않겠다.)
        // 쿠키에 expires 를 설정하면 영속쿠키가 되어 설정된 시간까지 살아있다.
        // 시간을 설정하지 않으면 세션쿠키로써 브라우저 종료시 삭제된다.
        // HttpServletResponse 에 세팅하면 된다
        response.addCookie(cookie);

        // 완료되었으니 홈으로 이동
        return "redirect:/home";

    }

    // 로그아웃 기능
    // 논리는 HttpServletResponse 에 바로 꺼지는 쿠키로 덮어씌우기
    @PostMapping("/signout")
    public String signOut (HttpServletResponse response) {
        // memberId 사라지게 만들고
        Cookie cookie = new Cookie("memberId",null);
        // 바로 꺼지는 쿠키로 덮기
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/home";
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
