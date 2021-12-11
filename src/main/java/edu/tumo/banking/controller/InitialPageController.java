package edu.tumo.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InitialPageController {

    public InitialPageController() {
    }

    @GetMapping()
    public String index() {
        return "index";
    }
}
