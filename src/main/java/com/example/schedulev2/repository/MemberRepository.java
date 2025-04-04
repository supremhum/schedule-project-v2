package com.example.schedulev2.repository;


import com.example.schedulev2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>{
    default Member findMemberByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Does not Exist id = " + id
                        )
                );
    }

    @Query("SELECT m FROM Member m WHERE (:email IS NULL OR m.email = :email) AND (:name IS NULL OR m.name = :name)")
    List<Member> search(
            @Param("email") String email,
            @Param("name") String name);

    Optional<Member> findMemberByEmail(String email);

    default Member findMemberByEmailOrElseThrow(String email) {
        return findMemberByEmail(email)
                .orElseThrow(()->
                        new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                                "Does not Exist email"
        ));

    }
}
