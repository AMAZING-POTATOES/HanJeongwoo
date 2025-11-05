package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime createDate;

    // N:1 (여러 답변이 하나의 질문에)
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;
}
