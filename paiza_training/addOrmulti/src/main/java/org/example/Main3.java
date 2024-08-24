package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        list.add(scanner.nextInt());
        list.add(scanner.nextInt());

        int sum = 0;
        for (Integer item : list) sum += item;

        System.out.println(sum);
        scanner.close();
    }
}