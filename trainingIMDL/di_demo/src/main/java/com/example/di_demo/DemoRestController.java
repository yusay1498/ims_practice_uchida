package com.example.di_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
    private Animal animal;

    public DemoRestController(Animal animal) {
        this.animal = animal;
    }

    @GetMapping("/")
    @ResponseBody
    public String get() {
        return animal.call();
    }
}
