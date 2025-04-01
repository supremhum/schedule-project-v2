package com.example.schedulev2.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;


@Getter
public class ScheduleUpdateRequestDto {
    @NotNull
    private final String author;
    @NotNull
    private final String title;
    @NotNull
    private final String description;
    private final String password;

    public ScheduleUpdateRequestDto(String author, String title, String description, String password) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.password = password;
    }
}
