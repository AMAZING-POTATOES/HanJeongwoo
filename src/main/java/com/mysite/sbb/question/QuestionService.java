package com.mysite.sbb.question;

import com.mysite.sbb.common.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDateTime;
import org.springframework.data.domain.*;

public Page<Question> getList(int page) {
    Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("createDate")));
    return questionRepository.findAll(pageable);
}


public Question create(String subject, String content) {
    Question q = new Question();
    q.setSubject(subject);
    q.setContent(content);
    q.setCreateDate(LocalDateTime.now());
    return questionRepository.save(q);
}

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("question not found"));
    }
}
