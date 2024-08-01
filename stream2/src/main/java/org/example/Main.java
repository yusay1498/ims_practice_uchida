package org.example;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<Demo> demoList = List.of(
                new Demo(1, "Alice", 7),
                new Demo(2, "Bob", 10),
                new Demo(3, "Carol", 13),
                new Demo(4, "David", 16)
        );

        boolean anyMatched = demoList.stream()
                .anyMatch(demo -> demo.name().startsWith("A"));

        System.out.println(anyMatched);

        boolean noneMatched = demoList.stream()
                .noneMatch(demo -> demo.name().startsWith("Z"));
        System.out.println(noneMatched);

        anyMatched = demoList.stream()
                .anyMatch(demo -> demo.name().startsWith("Z"));

        System.out.println(anyMatched);

        noneMatched = demoList.stream()
                .noneMatch(demo -> demo.name().startsWith("A"));
        System.out.println(noneMatched);

        Optional<Demo> firstOpt = demoList.stream()
                .filter(demo -> demo.name().startsWith("Z"))
                .findFirst();

        Demo foundDemo = firstOpt
                .orElse(new Demo(0, "Not found", -1));

        System.out.println(foundDemo);

        firstOpt.ifPresent(demo -> {

        });

//        firstOpt.orElseThrow(() -> new NullPointerException());

        Demo demoSummary = demoList.stream()
                .reduce(new Demo(0, "", 0), (demo1, demo2) -> {
                    return new Demo(
                            demo1.id() + demo2.id(),
                            demo1.name() + demo2.name(),
                            demo1.age() + demo2.age()
                    );
                });

        System.out.println(demoSummary);

        Map<Integer, Demo> demoMap = demoList.stream()
                .reduce(new HashMap<>(), (acc, cur) -> {
                    acc.put(cur.id(), cur);
                    return acc;
                }, (acc1, acc2) -> acc1);

        System.out.println(demoMap);

        Map<Integer, Demo> demoMap2 = demoList.stream()
                .collect(Collectors.toMap(
                        demo -> demo.id(),
                        demo -> demo
                ));

        System.out.println(demoMap2);

        List<String> nameList = demoList.stream()
                .map(demo -> demo.name())
                .map(name -> name + "san")
                .toList();

        System.out.println(nameList);
    }

}