package com.example.schedulev2.repository;


import com.example.schedulev2.dto.UpdatePasswordRequestDto;
import com.example.schedulev2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    default Member findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not Exist id = " + id
                        )
                );
    }
}
