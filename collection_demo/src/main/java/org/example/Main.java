package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> linkedList = new LinkedList<>();
        linkedList.add("aaa");
        linkedList.add("AAA");
        linkedList.add("ABC");
        linkedList.add("123");
        linkedList.add("e");
        linkedList.add("f");
        linkedList.add("g");

        System.out.println("--- LinkedList ---");
        linkedList.forEach(System.out::println);

        Set<String> linkedListSet = new LinkedHashSet<>();
        linkedListSet.add("aaa");
        linkedListSet.add("AAA");
        linkedListSet.add("ABC");
        linkedListSet.add("123");
        linkedListSet.add("e");
        linkedListSet.add("f");
        linkedListSet.add("g");

        System.out.println("--- LinkedHashSet ---");
        linkedListSet.forEach(System.out::println);

        Set<String> treeSet = new TreeSet<>();
        treeSet.add("aaa");
        treeSet.add("AAA");
        treeSet.add("ABC");
        treeSet.add("123");
        treeSet.add("e");
        treeSet.add("f");
        treeSet.add("g");

        System.out.println("--- TreeSet ---");
        treeSet.forEach(System.out::println);

        Map<String, Integer> hashMap = new HashMap<>();

        hashMap.put("AAA", 100);
        hashMap.put("bbb", 400);
        hashMap.put("CCC", 200);
        hashMap.put("ddd", 300);

        System.out.println("--- HashMap ---");
        hashMap.forEach((s, i) -> System.out.println(s + ": " + i));

        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap.put("AAA", 100);
        linkedHashMap.put("bbb", 400);
        linkedHashMap.put("CCC", 200);
        linkedHashMap.put("ddd", 300);

        System.out.println("--- LinkedHashMap ---");
        linkedHashMap.forEach((s, i) -> System.out.println(s + " : " + i));


        Map<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("AAA", 100);
        treeMap.put("BBB", 400);
        treeMap.put("CCC", 200);
        treeMap.put("ddd", 300);

        System.out.println("--- TreeMap ---");
        treeMap.forEach((s, i) -> System.out.println(s + " : " + i));
    }
}