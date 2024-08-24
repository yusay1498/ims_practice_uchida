package org.example;

import java.util.*;

public class Main6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(scanner.nextInt(), scanner.next());
        }

        for (int i = 0; i < K; i++) {
            int key = scanner.nextInt();
            System.out.println(map.get(key));
        }
    }
}
