package com.example.schedulev2.controller;

import com.example.schedulev2.dto.member.SignInRequestDto;
import com.example.schedulev2.dto.member.SignInResponseDto;
import com.example.schedulev2.service.HomeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final HomeService homeService;

    @PostMapping("/signin")
    public String signIn(
            @Valid @RequestBody SignInRequestDto requestDto,

            HttpServletRequest request
    ) {

        SignInResponseDto signInResponseDto = homeService.signIn(requestDto);

        // 아이디와 비밀번호가 틀렸습니다
        if (signInResponseDto==null) {
            return "seshon-signInPage";
        }

        // 아이디와 비밀번호가 맞았습니다
        // getSession(true) 가 default : 세션이 있으면 기존세션반환, 세션이 없으면 생성(해서 반환?)
        HttpSession session = request.getSession(); //

        // 회원정보를 조회하기
        SignInResponseDto findDto = homeService.findById(signInResponseDto.getId());

        // 세션에 로그인 멤버 정보 저장. Cont.ENUM 이 있긴하지만 키,값 으로 세션에 저장하겠다 는 의미
        session.setAttribute("SIGNIN_MEMBER",findDto);

        // 완료되었으니 홈으로 이동
        return "redirect:/seshon-homePage";
    }

    @PostMapping("/signout")
    public String signOut (HttpServletRequest request) {

        // getSession(false) 은 세션이 없다면 null 로 반환. 있으면 그거 반환
        HttpSession session = request.getSession(false);

        // 세션이 존재하면 (로그인된 경우를 말함)
        if (session != null) {
            // 해당 세션 삭제
            session.invalidate();
        }

        return "redirect:/seshon-signInPage";
    }

    @GetMapping("/home")
    public String home (HttpServletRequest request) {

        // 세션이 없는 경우 체크. 즉 로그인하지 않는 상태 정리.
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "seshon-signInPage-noSession";
        }

        // 세션이 있는경우다. 해당 정보를 response 정보로 담는다
        SignInResponseDto signinMember = (SignInResponseDto) session.getAttribute("SIGNIN_MEMBER");
        // 세션정보에 맞는 멤버 정보가 없다면 null 반환하니
        if (signinMember==null) {
            return "seshon-signInPage-notExist";
        }

        // 세션정보로 해당멤버를 잘 찾았으니 홈페이지 반환
        return "seshon-homePage";
    }

}
