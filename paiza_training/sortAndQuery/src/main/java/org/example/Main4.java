package org.example;

import java.util.*;

import static java.lang.reflect.Array.set;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            list.add(scanner.nextInt());
        }

        for (int i = 1; i < N; i++) {
            System.out.println(list.get(i));
        }
    }
}
