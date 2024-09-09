package org.example;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s = scanner.nextInt();
        int n = scanner.nextInt();
        // List の初期化
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        for (int i = 0; i < n; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("user", scanner.next());
            map.put("blood type", scanner.next());
            list.add(map);
        }

        // List の内容を表示
        for (Map<String, String> map : list) {
            System.out.println(map.get("user") + " " + map.get("blood type"));
        }
    }
}
