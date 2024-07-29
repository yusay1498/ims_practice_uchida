package com.example.di_demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Dog implements Animal{
    @Override
    public String call() {
        return "Baw Baw";
    }
}
