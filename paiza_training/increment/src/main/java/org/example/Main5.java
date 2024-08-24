package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main5 {
    public static void main(String[] args) {
        // 指定された要素を持つ ArrayList を作成
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(0);
        list.add(5);
        list.add(-1);
        list.add(3);
        list.add(10);
        list.add(6);
        list.add(-8);

        int sum = 0;
        for (Integer i : list) {
            if (i >= 5) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
