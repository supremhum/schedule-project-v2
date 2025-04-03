package com.example.schedulev2.repository;

import com.example.schedulev2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HomeRepository extends JpaRepository<Member,Long> {
    Optional<Member> findMemberByEmailAndPassword(String email,String password);

}
