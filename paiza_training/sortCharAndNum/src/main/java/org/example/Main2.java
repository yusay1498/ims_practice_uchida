package org.example;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int k = scanner.nextInt();
            list.add(k);
        }

        Collections.sort(list);

        for (Integer number : list) {
            System.out.println(number);
        }
    }
}
