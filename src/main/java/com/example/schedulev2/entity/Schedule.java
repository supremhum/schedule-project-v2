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

}
