package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer create(Question question, String content) {
        Answer a = new Answer();
        a.setQuestion(question);
        a.setContent(content);
        a.setCreateDate(LocalDateTime.now());
        return answerRepository.save(a);
    }
}
