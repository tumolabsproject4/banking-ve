package edu.tumo.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InitialPageController {

    public InitialPageController() {
    }

    public String initialPage() {
        return "initialPage";
    }
}
