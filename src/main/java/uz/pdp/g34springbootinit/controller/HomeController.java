package uz.pdp.g34springbootinit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("group", "G34");
        return "index";
    }
}
