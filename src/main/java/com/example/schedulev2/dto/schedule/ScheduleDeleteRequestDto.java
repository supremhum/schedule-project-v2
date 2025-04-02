package com.example.schedulev2.dto.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleDeleteRequestDto {

    @NotNull
    private final String password;

    public ScheduleDeleteRequestDto(String password) {
        this.password = password;
    }
}
