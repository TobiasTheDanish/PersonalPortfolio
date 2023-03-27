package com.tobiasthedanish.personalportfolio.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Test")
public class TestController {
    @GetMapping()
    public String load(Model model) {
        model.addAttribute("test_message", "Hello test page");

        return "test";
    }
}
