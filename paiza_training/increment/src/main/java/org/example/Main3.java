package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        // 指定された要素を持つ ArrayList を作成
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(6);
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(23);
        list.add(2);

        int sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        System.out.println(sum);
    }
}
