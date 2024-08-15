package org.example;

import java.util.*;

//年齢でsort

//入力例
//3
//mako 13 08/08 nara
//megumi 14 11/02 saitama
//taisei 16 12/04 nagano
//14

//出力
//mako 13 08/08 nara
//megumi 14 11/02 saitama
//taisei 16 12/04 nagano

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Map<String, Objects>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, Objects> user = new HashMap<>();
            user.put("nickname", scanner.next());
            user.put("old", Integer.parseInt(scanner.next()));
            user.put("birth", scanner.next());
            user.put("state", scanner.next());
            users.add(user);
        }



        for (Map<String, String> user : users) {

        }
    }
}
