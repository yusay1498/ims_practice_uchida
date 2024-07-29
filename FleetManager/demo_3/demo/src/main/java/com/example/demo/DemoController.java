package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DemoController {
    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute(
                "message",
                "Hello Thymeleaf");

        model.addAttribute(
                "listItems",
                List.of("Alice", "Bob", "Carol")
        );

        model.addAttribute(
                "condition",
                false
        );

        return "index";
    }

    @GetMapping("/form")
    public String getPurchaseForm() {
        return "form";
    }

    @GetMapping("/thx")
    public String getPurchaseThx() {
        return "thx";
    }

    @PostMapping("purchase")
    public String postPurchase() {
        System.out.println("Payment processing");
        return "redirect:/thx";
    }
}
