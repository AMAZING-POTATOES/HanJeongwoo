package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String subject;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private LocalDateTime createDate;

    // 질문 하나에 여러 답변(1:N)
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Answer> answerList;
}
