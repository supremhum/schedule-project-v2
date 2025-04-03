package com.example.schedulev2.controller;


import com.example.schedulev2.dto.member.MemberByIdResponseDto;
import com.example.schedulev2.dto.member.SignInRequestDto;
import com.example.schedulev2.dto.member.SignInResponseDto;
import com.example.schedulev2.service.HomeService;
import com.example.schedulev2.service.HomeServiceImpl;
import com.example.schedulev2.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
            return "signInPage-noCookie";
        }

        // 쿠키에 값이 있는경우니 그 값이 실제 DB에 있는지 확인해봅시다
        // memberId로 DB에서 멤버 Entity의 값을 Dto에 담아 왔다고 가정입니다
        SignInResponseDto findMember = homeService.findById(memberId);


        // 멤버를 찾을수 없다면 해당 아이디의 멤버가 없는 경우니 로그인페이지로 돌아감
        if(findMember == null) {
            return "signInPage-notExist";
        }

        // 쿠키의 멤버id가 정상적인경우까지 왔으니 홈페이지로 반환
        return "homePage";
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
        SignInResponseDto signInResponseDto = homeService.signIn(requestDto);

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

}
