package com.example.schedulev2.repository;

import com.example.schedulev2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<Member,Long> {

}
