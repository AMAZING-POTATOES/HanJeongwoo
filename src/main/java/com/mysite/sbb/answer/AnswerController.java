package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/answer/create/{id}")
    public String createAnswer(
            @PathVariable("id") Integer id,
            @Valid AnswerForm answerForm,
            BindingResult bindingResult,
            Model model
    ) {
        Question q = questionService.getQuestion(id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", q);
            return "question_detail";
        }
        answerService.create(q, answerForm.getContent());
        return "redirect:/question/detail/" + id;
    }
}
