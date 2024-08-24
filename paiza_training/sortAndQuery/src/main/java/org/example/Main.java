package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int Q = scanner.nextInt();

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            list.add(scanner.nextInt());
        }

        list.add(K, Q);

        for (Integer i : list) {
            System.out.println(i);
        }
    }
}