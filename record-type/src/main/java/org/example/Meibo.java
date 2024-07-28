package org.example;

public record Meibo(int number, String name, String mail) {
    public void println() {
        System.out.println(number());
        System.out.println(name());
        System.out.println(mail());
    }
}
