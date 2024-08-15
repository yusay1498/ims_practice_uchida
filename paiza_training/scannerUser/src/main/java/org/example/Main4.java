package org.example;

import java.util.*;

//user数　更新回数
//userの情報
//userの番号 新しいuserの名前

//入力例
//3 2
//mako 13 08/08 nara
//taisei 16 12/04 nagano
//megumi 14 11/02 saitama
//2 taihei
//3 megu

//出力例
//mako 13 08/08 nara
//taihei 16 12/04 nagano
//megu 14 11/02 saitama

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.next());
        int M = Integer.parseInt(scanner.nextLine());
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("nickname", scanner.next());
            user.put("old", Integer.parseInt(scanner.next()));
            user.put("birth", scanner.next());
            user.put("state", scanner.next());
            users.add(user);
        }

        for (int i = 0; i < M; i++) {
            int K = Integer.parseInt(scanner.next());
            users.get(K + 1).get("nickname") = scanner.next();
        }

        for (Map<String, Object> user : users) {
            System.out.println(user.get("nickname") + " " + user.get("old") + " " + user.get("birth") + " " + user.get("state"));
        }
    }
}
