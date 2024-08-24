package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            list.add(scanner.nextInt());
        }

        for (int i = 0; i < K; i++) {
            String s = scanner.next();
            if (s.equals("pop")) {
                list.removeFirst();
            } else if (s.equals("show")) {
                for (Integer integer : list) {
                    System.out.println(integer);
                }
            }
        }
    }
}
