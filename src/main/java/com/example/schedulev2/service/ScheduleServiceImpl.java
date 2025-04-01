package com.example.schedulev2.service;

import com.example.schedulev2.dto.CreateScheduleRequestDto;
import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.entity.Member;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.repository.MemberRepository;
import com.example.schedulev2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    @Override
    public ScheduleResponseDto save(CreateScheduleRequestDto requestDto) {

        Member findMember = memberRepository.findMemberByEmailOrElseThrow(requestDto.getEmail());

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getAuthor(),
                requestDto.getDescription()
        );
        schedule.setMember(findMember);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getAuthor(),savedSchedule.getTitle(),savedSchedule.getDescription());
    }
}
