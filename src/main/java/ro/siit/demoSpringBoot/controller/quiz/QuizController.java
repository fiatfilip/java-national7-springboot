package ro.siit.demoSpringBoot.controller.quiz;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="nume", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("nameX", name);
        return "greeting/greeting";
    }

}
