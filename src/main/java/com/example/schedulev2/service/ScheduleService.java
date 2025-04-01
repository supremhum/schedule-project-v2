package com.example.schedulev2.service;

import com.example.schedulev2.dto.CreateScheduleRequestDto;
import com.example.schedulev2.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {
    ScheduleResponseDto save(CreateScheduleRequestDto requestDto);
}
