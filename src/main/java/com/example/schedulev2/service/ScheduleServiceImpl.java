package com.example.schedulev2.service;

import com.example.schedulev2.dto.schedule.ScheduleCreateRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleDeleteRequestDto;
import com.example.schedulev2.dto.schedule.ScheduleResponseDto;
import com.example.schedulev2.dto.schedule.ScheduleUpdateRequestDto;
import com.example.schedulev2.entity.Member;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.repository.MemberRepository;
import com.example.schedulev2.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;

//    @Transactional
    @Override
    public ScheduleResponseDto save(ScheduleCreateRequestDto requestDto) {

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


    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto dto) {
        // id에 해당하는 memberId를 가지고 member에서 찾은 뒤 거기에 있는 비밀번호와 dto의 비밀번호가 맞는지 확인한다
        Schedule findById = scheduleRepository.findByIdOrElseThrow(id);
//        findById.getMember().getPassword();
        if (!findById.getMember().getPassword().equals(dto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호 불일치");
        }
        // 비밀번호가 맞았으니 수정이 들어간다
        findById.updateSchedule(dto.getTitle(),dto.getAuthor(),dto.getDescription());
        return new ScheduleResponseDto(findById.getId(),findById.getAuthor(),findById.getTitle(),findById.getDescription());

    }

    @Override
    public void delete(Long id, ScheduleDeleteRequestDto requestDto) {
        Schedule findById = scheduleRepository.findByIdOrElseThrow(id);
        if (!findById.getMember().getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호 불일치");
        }
        scheduleRepository.delete(findById);
    }

}
