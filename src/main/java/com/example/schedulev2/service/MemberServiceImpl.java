package com.example.schedulev2.service;

import com.example.schedulev2.dto.member.*;
import com.example.schedulev2.entity.Member;
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
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        Member member = new Member(requestDto.getEmail(),requestDto.getPassword(),requestDto.getName());
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

    @Transactional
    @Override
    public MemberResponseDto updateById(Long id, MemberUpdateRequestDto requestDto) {
        Member findMember = memberRepository.findMemberByIdOrElseThrow(id);
        if (!findMember.getPassword().equals(requestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Password does not match");
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



}
