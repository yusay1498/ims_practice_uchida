package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@RequestMapping("/greeting")
@Controller
public class GreetingController {
    @GetMapping
    @ResponseBody
    public String get(@RequestParam("name") String name) {
        return HtmlUtils.htmlEscape("ようこそ" + name + "さん");
    }
}
