package com.example.schedulev2.service;

import com.example.schedulev2.dto.CreateScheduleRequestDto;
import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.entity.Member;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.repository.MemberRepository;
import com.example.schedulev2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

//    @Transactional
    @Override
    public ScheduleResponseDto save(CreateScheduleRequestDto requestDto) {

        Member findMember = memberRepository.findMemberByEmailOrElseThrow(requestDto.getEmail());

        Schedule schedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getAuthor(),
                requestDto.getDescription()
        );
        schedule.setMember(findMember);

        // save 매서드 안에 이미 Transactional 이 붙어있어서 상단에 어노테이션이 필요 없는것
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getAuthor(),savedSchedule.getTitle(),savedSchedule.getDescription());
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
//        List<Schedule> findAllSchedule= scheduleRepository.findAll();
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();

    }

    @Override
    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(),findSchedule.getAuthor(),findSchedule.getTitle(),findSchedule.getDescription());
    }

    @Override
    public void delete(Long id) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(schedule);
    }
}
