package org.example;

import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<String, Integer> map = new HashMap<>();

        // 入力の読み込みとマップへの格納
        for (int i = 0; i < n; i++) {
            String s = scanner.next();
            int k = scanner.nextInt();
            map.put(s, map.getOrDefault(s, 0) + k);
        }

        // マップのエントリーをリストに変換
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        // 数値で降順にソート
        list.sort((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()));

        // ソートされたエントリーの出力
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
