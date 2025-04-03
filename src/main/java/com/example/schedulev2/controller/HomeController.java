package com.example.schedulev2.controller;


import com.example.schedulev2.dto.member.SignInResponseDto;
import com.example.schedulev2.service.HomeService;
import com.example.schedulev2.service.HomeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    // url home 으로 들어오는 상황이다.
    // 1. 쿠키를 파악해서 로그인 정보가 있는지 확인한다
    // 1-1 있다면 정상적 진행
    // 1-2 없다면 로그인요청(return signInPage)으로 응답
    @GetMapping("/home")
    public String home (
            // 쿠키값은 필수가 아니기에 requered 가 false
            // 모델객체가 뭔지 모르겠으니깐 일단 넘어갑시다
            @CookieValue(name="memberId",required = false) Long memberId)
    {
        // 이부분은 쿠키에 값이 있는지 없는지 판별. 쿠키에 값이 없으면 로그인요청입니다
        if(memberId == null) {
            return "signInPage";
        }

        // 쿠키에 값이 있는경우니 그 값이 실제 DB에 있는지 확인해봅시다
        // memberId로 DB에서 멤버 Entity의 값을 Dto에 담아 왔다고 가정입니다
        SignInResponseDto findMember = homeService.findById(memberId);

        // 멤버를 찾을수 없다면 해당 아이디의 멤버가 없는 경우니 로그인페이지로 돌아감
        if(findMember == null) {
            return "signInPage";
        }

        // 쿠키의 멤버id가 정상적인경우까지 왔으니 홈페이지로 반환
        return "homePage";
    }

}
