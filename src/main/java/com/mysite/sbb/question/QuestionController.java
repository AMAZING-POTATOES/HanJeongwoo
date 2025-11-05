@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model) {
        model.addAttribute("questionList", questionService.getList());
        return "question_list";
    }
}

import org.springframework.web.bind.annotation.PathVariable;

@GetMapping("/question/detail/{id}")
public String detail(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("question", questionService.getQuestion(id));
    return "question_detail";
}