package com.example.schedulev2.service;

import com.example.schedulev2.dto.schedule.*;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(ScheduleCreateRequestDto requestDto);

    List<ScheduleResponseDto> findAll();

    ScheduleDetailResponseDto findById(Long id);

    void delete(Long id, ScheduleDeleteRequestDto requestDto);

    ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto);
}
