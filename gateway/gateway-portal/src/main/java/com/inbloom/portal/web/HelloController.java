package com.inbloom.portal.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By: paullawler
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("message", "Hi there, World!");
        return "hello";
    }

    @RequestMapping("/thymeleaf/layout")
    public String content1() {
        return "content";
    }

}
