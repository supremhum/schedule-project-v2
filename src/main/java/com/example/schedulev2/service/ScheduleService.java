package com.example.schedulev2.service;

import com.example.schedulev2.dto.CreateScheduleRequestDto;
import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.dto.ScheduleUpdateRequestDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(CreateScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findById(Long id);

    void delete(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto);
}
