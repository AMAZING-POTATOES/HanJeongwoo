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
