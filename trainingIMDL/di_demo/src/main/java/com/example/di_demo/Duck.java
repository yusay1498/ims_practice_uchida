package com.example.di_demo;

import org.springframework.stereotype.Component;

@Component
public class Duck implements Animal{
    @Override
    public String call() {
        return "Quack";
    }
}
