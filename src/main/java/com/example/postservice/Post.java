package com.example.postservice;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "authorId")
    private Long authorId;

    @Column(name = "text")
    private String text;

    @Column(name = "postedAt")
    private LocalDateTime postedAt;

    @Column(name = "topic")
    private String topic;
}
