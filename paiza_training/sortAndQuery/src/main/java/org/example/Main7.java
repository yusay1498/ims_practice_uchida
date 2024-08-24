package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(scanner.nextInt(), scanner.next());
        }

        for (int i = 0; i < K; i++) {
            String s = scanner.next();
            int key = scanner.nextInt();
            switch (s) {
                case "call" -> System.out.println(map.get(key));
                case "leave" -> map.remove(key);
                case "join" -> map.put(key, scanner.next());
            }
        }
    }
}
