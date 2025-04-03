package com.example.schedulev2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j // 로그 작성을 위한 어노테이션
public class LoginFilter implements Filter {

    // 화이트 리스트에 해당 URL 을 추가한다
    private static final String[] WHITE_LIST = {"/api/session/*","/api/cookie/*","/api/signup"};

    // 먼저 화이트 리스트 경로인지 확인하는 **메서드**
    // 보통 불리언타입을 반환할때 매서드는 is 를 붙인다. true false 니깐
    private boolean isWhiteList(String requestURI) {
        // 화이트리스트에 만든 URL 을 포함하지 않는다면 false다
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }

    // 이제 실제 인증로직을 위해서 두필터를 구현해보자
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        // 리퀘스트를 다운캐스팅 하자
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // http request를 통해 URI를 변수로 담자
        String requestURI = httpRequest.getRequestURI();

        // response 객체도 편하게 다루기 위해 다운캐스팅 한다
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 위의 사전작업이 완료되면 밑의 로그가 찍히게 된다. 이는 @Slf4j 어노테이션으로 가능한것
        log.info("로그인 필터 로직 실행");

        // 로그인을 체크해야하는 URL인지 확인하기
        if (!isWhiteList(requestURI)) {

            log.info("화이트리스트에 없는 URL. 이쪽 분기를 탔습니다"); //즉 이미 로그인된 세계선 ??

            // getSession(true)	세션이 없으면 새로 생성	있으면 반환, 없으면 생성
            // getSession(false)	세션이 없으면 생성하지 않음	있으면 반환, 없으면 null 반환
            HttpSession session = httpRequest.getSession(false);
//            log.info(session.getId());

            if (session == null || session.getAttribute("SIGNIN_MEMBER")==null) {
                // 세션이 없는 세계선입니다
                log.info("로그인 해주세요");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
//                throw new RuntimeException("로그인 해주세요.");
            }

            // 로그인 성공 로직
            log.info("로그인에 성공했습니다.");


        }


        // 1번의 경우 : WHITE_LIST 에 등록된 URL 요청이라면 chain.doFilter() 호출
        // 2번의 경우 : WHITE_LIST 에 등록된 URL 아닌요쳥은 위에있는 필터로직 통과후에
        // chain.doFilter() 다음 필터나 서블릿을 호출
        filterChain.doFilter(request, response);
        // 다음 필터가 없으면 서블릿 -> 컨트롤러, 다음필터가 있으면 다음 필터를 호출한다.



    }


}