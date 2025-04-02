package com.example.schedulev2.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {

    @NotNull
    private final String email;
    private final String author;
    private final String title;
    private final String description;

    public ScheduleCreateRequestDto(String email, String author, String title, String description) {
        this.email=email;
        this.author = author;
        this.title = title;
        this.description = description;
    }
}
