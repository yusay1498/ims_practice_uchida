package org.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] airports = {"HND", "NRT", "KIX", "NGO", "NGO"};

        // 配列をSetに変換して重複を排除
        Set<String> set = new HashSet<>();
        Collections.addAll(set, airports);

        // 配列の要素数とSetのサイズを比較
        System.out.println(set.size() < airports.length);
    }
}
