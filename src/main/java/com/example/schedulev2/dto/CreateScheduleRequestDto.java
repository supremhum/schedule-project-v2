package com.example.schedulev2.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private String email;
    private String author;
    private String title;
    private String description;

    public CreateScheduleRequestDto(String email,String author,String title,String description) {
        this.email=email;
        this.author = author;
        this.title = title;
        this.description = description;
    }
}
