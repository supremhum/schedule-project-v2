package com.example.schedulev2.dto;

import com.example.schedulev2.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
        private final Long scheduleId;
        private final String author;
        private final String title;
        private final String description;

    public ScheduleResponseDto(Long scheduleId,String author, String title, String description) {
        this.scheduleId = scheduleId;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(),schedule.getAuthor(), schedule.getTitle(), schedule.getDescription());
    }
}
