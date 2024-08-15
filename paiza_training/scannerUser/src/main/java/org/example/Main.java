package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Map<String, String> user = new HashMap<>();
        for (int i = 0; i < N; i++) {
             user = Map.of(
                    "nickname" , scanner.next(),
                    "old", scanner.next(),
                    "birth", scanner.next(),
                    "state", scanner.next()
            );
        }
        for (int i = 0; i < N; i++) {
            System.out.println(user.get(i));
        }
    }
}