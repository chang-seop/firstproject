package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username", "Chang Seop"); //username변수에 데이터에 보내기
        return "greetings"; // templates/greetings.mustache -> 브라우저 전송!
    }

    @GetMapping("/bye")
    public String niceBye(Model model) {
        model.addAttribute("username", "Chang Seop");
        return "bye";
    }
}
