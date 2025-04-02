package com.example.schedulev2.controller;

import com.example.schedulev2.dto.schedule.ScheduleCreateRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleDeleteRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedulev2.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleCreateRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);

    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList,HttpStatus.OK);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto responseDto = scheduleService.findById(id);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    // 해당 id의 이름,제목,내용을 전부 바꾼다. 모두 notnull . 비밀번호 필요
    @PutMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> updateById(@PathVariable Long id,@Valid @RequestBody ScheduleUpdateRequestDto dto) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    // 해당 id의 이름 또는 제목 또는 내용을 바꾼다. null 인 경우는 바꾸지 않는다(Schedule Entity 내부 method 로직). 비밀번호 필요
    @PatchMapping("/schedules/{id}")
    public ResponseEntity<ScheduleResponseDto> patchById(@PathVariable Long id,@RequestBody ScheduleUpdateRequestDto dto) {
        ScheduleResponseDto responseDto = scheduleService.updateSchedule(id, dto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }


    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody ScheduleDeleteRequestDto requestDto) {
        scheduleService.delete(id,requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
