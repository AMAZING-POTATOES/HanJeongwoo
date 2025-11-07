package com.mysite.sbb;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void step01_saveQuestions() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("스프링 부트 게시판 프로젝트(SBB)에 대해 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링 데이터 JPA가 무엇인가요?");
        q2.setContent("ORM과 리포지터리 사용법을 알고 싶습니다.");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        assertTrue(q1.getId() != null && q2.getId() != null);
    }

    @Test
    void step02_findAllAndById() {
        List<Question> all = questionRepository.findAll();
        assertTrue(all.size() >= 2);

        Optional<Question> oq = questionRepository.findById(all.get(0).getId());
        assertTrue(oq.isPresent());
    }

    @Test
    void step03_findBySubjectVariants() {
        questionRepository.findBySubject("sbb가 무엇인가요?")
                .ifPresent(q -> assertEquals("sbb가 무엇인가요?", q.getSubject()));

        questionRepository.findBySubjectAndContent(
                "스프링 데이터 JPA가 무엇인가요?",
                "ORM과 리포지터리 사용법을 알고 싶습니다."
        ).ifPresent(q -> assertEquals("스프링 데이터 JPA가 무엇인가요?", q.getSubject()));

        List<Question> like = questionRepository.findBySubjectLike("sbb%");
        assertNotNull(like);
    }

    @Test
    void step04_updateQuestion() {
        Question q = questionRepository.findBySubject("sbb가 무엇인가요?")
                .orElseThrow();
        q.setSubject("수정된 제목");
        questionRepository.save(q);

        Question updated = questionRepository.findById(q.getId()).orElseThrow();
        assertEquals("수정된 제목", updated.getSubject());
    }

    @Test
    void step05_deleteOne() {
    
        questionRepository.findBySubject("수정된 제목")
                .ifPresent(questionRepository::delete);

        assertTrue(questionRepository.count() >= 1);
    }

    @Test
    void step06_saveAnswerAndQuery() {

        Question q = questionRepository.findAll().stream().findFirst().orElseThrow();

        Answer a = new Answer();
        a.setContent("질문에 대한 답변입니다.");
        a.setCreateDate(LocalDateTime.now());
        a.setQuestion(q);
        answerRepository.save(a);

        Answer got = answerRepository.findById(a.getId()).orElseThrow();
        assertEquals(q.getId(), got.getQuestion().getId());
    }

    @Test
    @Transactional 
    void step07_questionToAnswers() {
        Question q = questionRepository.findAll().stream().findFirst().orElseThrow();
        List<Answer> answers = q.getAnswerList(); 
        assertNotNull(answers);
    }
    @Test
    void bulkInsertQuestions() {
        for (int i = 1; i <= 300; i++) {
            Question q = new Question();
            q.setSubject("테스트 질문 " + i);
            q.setContent("내용 " + i);
            q.setCreateDate(LocalDateTime.now());
            questionRepository.save(q);
        }
    }

}
