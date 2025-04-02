package com.example.schedulev2.dto.schedule;

import com.example.schedulev2.entity.Schedule;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class ScheduleDetailResponseDto {
    private final Long scheduleId;
    private final String email;
    private final String author;
    private final String title;
    private final String description;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleDetailResponseDto(Long scheduleId, String email, String author, String title, String description, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.scheduleId = scheduleId;
        this.email = email;
        this.author = author;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static ScheduleDetailResponseDto toDtoV2(Schedule schedule, String email) {
        return new ScheduleDetailResponseDto(
                schedule.getId(),
                email, // member DB에 있는 이메일 업어오기
                schedule.getAuthor(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }
}
