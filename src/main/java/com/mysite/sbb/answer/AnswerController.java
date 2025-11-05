package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/answer/create/{id}")
    public String createAnswer(
            @PathVariable("id") Integer id,
            @RequestParam("content") String content,
            RedirectAttributes ra
    ) {
        Question q = questionService.getQuestion(id);
        answerService.create(q, content);
        ra.addFlashAttribute("msg", "답변이 등록되었습니다.");
        return "redirect:/question/detail/" + id;
    }
}
