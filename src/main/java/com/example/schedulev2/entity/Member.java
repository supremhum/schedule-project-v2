package com.example.schedulev2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Entity
@Table(name="member")
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public Member(String email,String password,String name) {
        this.name=name;
        this.email=email;
        this.password=password;
    }

    // 로그인을 위한 생성자
    public Member(Long id,String email,String password,String name) {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public Member() {
    }

    public void updatePassword(String newPassword) {
            this.password=newPassword;
    }

    public void updateNameOrEmail(String name,String email) {
        if (StringUtils.hasText(name)) {
            this.name=name;
        } if (StringUtils.hasText(email)) {
            this.email=email;
        }

    }
}
