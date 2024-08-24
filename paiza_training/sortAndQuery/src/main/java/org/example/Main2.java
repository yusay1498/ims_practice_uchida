package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < N; i++) {
            list.add(scanner.nextInt());
        }

        int count = 0;

        for (Integer i : list) {
            if (i == K) {
                count++;
            }
        }

        if (count == 0) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}