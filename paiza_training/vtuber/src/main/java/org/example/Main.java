package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < N; i++) {
            String name = scanner.next();
            list.add(name);
        }

        Collections.sort(list);

        for (int i = 0; i < K; i++) {
            String event = scanner.next();
            switch (event) {
                case "handshake" -> {
                    for (String name : list) {
                        System.out.println(name);
                    }
                }
                case "leave" -> {
                    String name = scanner.next();
                    list.remove(name);
                }
                case "join" -> {
                    String name = scanner.next();
                    list.add(name);
                }
            }
            Collections.sort(list);
        }
    }
}