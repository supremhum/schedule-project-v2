package com.example.schedulev2.dto.schedule;

import com.example.schedulev2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
        private final Long scheduleId;
        private final String author;
        private final String title;
        private final LocalDate createdAt;
        private final LocalDate modifiedAt;

    public ScheduleResponseDto(Long scheduleId, String author, String title,  LocalDateTime createdAt,LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.author = author;
        this.title = title;
        this.createdAt = createdAt.toLocalDate();
        this.modifiedAt = modifiedAt.toLocalDate();
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(schedule.getId(),schedule.getAuthor(), schedule.getTitle(),schedule.getCreatedAt(),schedule.getModifiedAt());
    }
}
