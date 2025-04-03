package com.example.schedulev2.service;

import com.example.schedulev2.dto.member.*;
import com.example.schedulev2.entity.Member;
import com.example.schedulev2.entity.Schedule;
import com.example.schedulev2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional // .save() 안에 Transactional 이 있어서 딱히 필요는 없지만 일단 명시
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        Member member = new Member(requestDto.getEmail(),requestDto.getPassword(),requestDto.getName());
        if(memberRepository.findMemberByEmail(requestDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일 입니다");
        }
        Member saveMember = memberRepository.save(member);
        return new SignUpResponseDto(saveMember.getId(), saveMember.getEmail(), saveMember.getName());
    }



//    @Override
//    public List<MemberResponseDto> findAll() {
//        return memberRepository.findAll()
//                .stream()
//                .map(MemberResponseDto::toDto)
//                .toList();
//    }

    @Override
    public List<MemberResponseDto> search(MemberSearchRequestDto dto) {
        Specification<Member> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasText(dto.getEmail())) {
                predicates.add(cb.equal(root.get("email"), dto.getEmail()));
            }
            if (StringUtils.hasText(dto.getName())) {
                predicates.add(cb.equal(root.get("name"), dto.getName()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        List<Member> members = memberRepository.findAll(spec);
        return members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public MemberByIdResponseDto findById(Long id) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if (optionalMember.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id : " + id);
        }
        Member member = optionalMember.get();
        return new MemberByIdResponseDto(member.getEmail(), member.getName(),member.getCreatedAt(),member.getModifiedAt());
    }

    // 트러블슈팅 발생
    // 자바의 대소문자 구분과 DB에서 찾는건 대소문자 구분없이 하는것의 차이에 의해 발생한 사건
    @Transactional
    @Override
    public MemberResponseDto updateById(Long id, MemberUpdateRequestDto requestDto) {
        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);
        if (!findMember.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password does not match");
        }

        // 이메일을 찾는다 일단 getEmail이 null 이 아닌 경우에서만.
        // 애초에 이메일이 기존 이메일이랑 같으면 넘어가고 아니면
        if (requestDto.getEmail()!=null && !Objects.equals(requestDto.getEmail(),findMember.getEmail())) {
            // 요까진 입력된 이메일이 기존 이메일과 다른 경우다.

            Optional<Member> memberByEmail = memberRepository.findMemberByEmail(requestDto.getEmail());

            // 이제 dto 이메일을 가진 데이터를 찾고 없으면 넘기고 있으면 예외
            // 추가로 &&이후 찾은 데이터의 id가 기존id와 같으면 탈출
            if (memberByEmail.isPresent()&&!memberByEmail.get().getId().equals(id)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일 입니다");
            }
        }

        findMember.updateNameOrEmail(requestDto.getName(),requestDto.getEmail());

        return new MemberResponseDto(findMember);
    }


    @Transactional
    @Override
    public void updatePassword(Long id, PasswordUpdateRequestDto passwordRequestDto) {
        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);

        if (!findMember.getPassword().equals(passwordRequestDto.getOldPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password does not match");
        }

        // findMember.setPassword(); <<< 요 매소드만 세터다.
        findMember.updatePassword(passwordRequestDto.getNewPassword());
    }

    @Override
    public void delete(Long id,MemberDeleteRequestDto requestDto) {
        Member member = memberRepository.findMemberByIdOrElseThrow(id);
        if (!member.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password does not match");
        }
        memberRepository.delete(member);

    }

}
