package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        System.out.println(Colors.CYAN);
        System.out.println(Colors.YELLOW);

        System.out.println(Colors.CYAN.toString());
        System.out.println(Colors.YELLOW.toString());

        System.out.println(Colors.CYAN.name());
        System.out.println(Colors.YELLOW.name());

        System.out.println(Colors.RED.ordinal());
        System.out.println(Colors.CYAN.ordinal());
        System.out.println(Colors.YELLOW.ordinal());
    }
}