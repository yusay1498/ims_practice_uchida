package org.example;

import java.util.*;

//入力例
//3
//mako 13 08/08 nara
//megumi 14 11/02 saitama
//taisei 16 12/04 nagano
//14

//出力
//megumi

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Map<String, String>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, String> user = new HashMap<>();
            user.put("nickname", scanner.next());
            user.put("old", scanner.next());
            user.put("birth", scanner.next());
            user.put("state", scanner.next());
            users.add(user);
        }
        int age = scanner.nextInt();
        for (Map<String, String> user : users) {
            if (user.get("old").equals(String.valueOf(age))) {
                System.out.println(user.get("nickname"));
            }
        }
    }
}
