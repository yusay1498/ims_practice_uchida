package org.example;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(scanner.nextInt());
        }

        for (int i = 0; i < Q; i++) {
            int x = scanner.nextInt();
            if (set.contains(x)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
