package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main2 {
    public static void main(String[] args) {
        String[] airports = {"HND", "NRT", "KIX", "NGO", "NGO", "NGO", "NGO", "NGO"};

        // 要素の出現回数をカウントするためのマップ
        Map<String, Integer> countMap = new HashMap<>();

        // 配列をループして出現回数をカウント
        for (String airport : airports) {
            countMap.put(airport, countMap.getOrDefault(airport, 0) + 1);
        }

        // 出現回数が2回以上の要素の数をカウントし、その数を出力
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getValue());
            }
        }
    }
}
