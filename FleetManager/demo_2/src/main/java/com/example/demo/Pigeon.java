package com.example.demo;

public class Pigeon implements Animal{

    @Override
    public String call() {
        return "Coo";
    }

    public String fly() {
        return "I can fly";
    }
}
