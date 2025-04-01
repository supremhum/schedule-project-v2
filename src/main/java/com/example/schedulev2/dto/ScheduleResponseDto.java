package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {
//        private Long memberId;
        private String author;
        private String title;
        private String description;

    public ScheduleResponseDto(String author, String title, String description) {
//        this.memberId = memberId;
        this.author = author;
        this.title = title;
        this.description = description;
    }
}
