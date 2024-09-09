package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // List の初期化
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        // 各ユーザーと血液型の情報を含む Map を作成し、List に追加
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("user", "Kyoko");
        map1.put("blood type", "B");
        list.add(map1);

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("user", "Rio");
        map2.put("blood type", "O");
        list.add(map2);

        Map<String, String> map3 = new HashMap<String, String>();
        map3.put("user", "Tsubame");
        map3.put("blood type", "AB");
        list.add(map3);

        Map<String, String> map4 = new HashMap<String, String>();
        map4.put("user", "KurodaSensei");
        map4.put("blood type", "A");
        list.add(map4);

        Map<String, String> map5 = new HashMap<String, String>();
        map5.put("user", "NekoSensei");
        map5.put("blood type", "A");
        list.add(map5);

        // List の内容を表示
        for (Map<String, String> map : list) {
            System.out.println(map.get("user") + " " + map.get("blood type"));
        }
    }
}
