package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        // 指定された要素を持つ ArrayList を作成
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }

        int sum = 0;
        for (Integer i : list) {
            if (i >= 5) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
