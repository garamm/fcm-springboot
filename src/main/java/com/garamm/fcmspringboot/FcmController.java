package com.garamm.fcmspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FcmController {

    @GetMapping("/")
    private String index() {
        return "index";
    }

}
