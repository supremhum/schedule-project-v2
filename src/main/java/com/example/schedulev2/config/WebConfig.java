package com.example.schedulev2.config;

import com.example.schedulev2.filter.CustomFilter;
import com.example.schedulev2.filter.LoginFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<Filter> customFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        // Filter 등록
        filterRegistrationBean.setFilter(new CustomFilter());
        // Filter 순서 설정
        filterRegistrationBean.setOrder(1);
        // 전체 URL에 Filter 적용
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }


    @Bean
    public FilterRegistrationBean<Filter> loginFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginFilter());
        // 위에 filterRegistrationBean.setOrder(1) 가있으니 2로 설정
        filterRegistrationBean.setOrder(2);

        // 만약 "/post/*" 라고 해주면 나중에 변경될 요지가 많다. 즉 URL 변경될때마다 바꿔줘야 하는 번거로움이 생기니
        // 화이트리스트 만들어서 특정 url 외엔 인증로직 하게끔 만든것이다.
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
