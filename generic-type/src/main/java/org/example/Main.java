package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> stringList = List.of("AAA");

        GenericDemo<Integer> demo1 = new GenericDemo<>(100);
        int demo1Value = demo1.getValue();

        GenericDemo<String> demo2 = new GenericDemo<>("AAA");
        String demo2Value = demo2.getValue();

        System.out.println(demo1Value);
        System.out.println(demo2Value);

        List anyList = List.of("200", 100);

        anyList.forEach(o -> {
            if (o instanceof String s) {
                System.out.println(Integer.parseInt(s) * 10);
            } else if (o instanceof Integer i) {
                System.out.println(i * 10);
            }
        });
    }
}