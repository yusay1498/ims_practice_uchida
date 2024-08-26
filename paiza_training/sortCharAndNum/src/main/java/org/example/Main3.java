package org.example;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String s = scanner.next();
            int k = scanner.nextInt();
            map.put(k, s);
        }

        // Create a list from the map entries
        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());

        // Sort the list by the map's keys (the integers)
        list.sort(Map.Entry.comparingByKey());

        // Print the sorted entries
        for (Map.Entry<Integer, String> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
