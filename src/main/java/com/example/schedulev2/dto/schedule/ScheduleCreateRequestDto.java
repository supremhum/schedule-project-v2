package com.example.schedulev2.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleCreateRequestDto {


    @NotNull private final String email;
    @NotNull private final String author;
    @NotNull private final String title;
    @NotNull private final String description;

    public ScheduleCreateRequestDto(String email, String author, String title, String description) {
        this.email=email;
        this.author = author;
        this.title = title;
        this.description = description;
    }
}
