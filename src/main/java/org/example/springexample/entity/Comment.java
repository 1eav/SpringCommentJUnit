package org.example.springexample.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY) // По умолчанию в аннотации ManyToOne используется FetchType.Eager
    @JoinColumn(name = "author_id")
    private Author author;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}