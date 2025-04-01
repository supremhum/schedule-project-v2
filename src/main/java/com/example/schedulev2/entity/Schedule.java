package com.example.schedulev2.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String author;

    @Column(columnDefinition = "longtext")
    private String description;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    public Schedule(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public Schedule() {}

    public void setMember(Member member) {
        this.member = member;
    }
}
