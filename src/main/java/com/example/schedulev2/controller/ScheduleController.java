package com.example.schedulev2.controller;

import com.example.schedulev2.dto.schedule.*;
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
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleCreateRequestDto requestDto) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);

    }

    // yyyy-mm-dd 까지만 표기
    // 일정번호,작성자,제목,생성,수정일
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList,HttpStatus.OK);
    }

    // yyyy-mm-ddTHH:mm:ss.microsecond 표기
    // 일정번호,작성자,이메일,제목,내용,생성,수정일
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleDetailResponseDto> findById(@PathVariable Long id) {
        ScheduleDetailResponseDto responseDto = scheduleService.findById(id);
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

    // id에 해당하는 일정 삭제. 비밀번호 필요
    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody ScheduleDeleteRequestDto requestDto) {
        scheduleService.delete(id,requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
