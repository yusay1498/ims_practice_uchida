package org.example;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Map<String, String>> users = new ArrayList<Map<String, String>>();

        for (int i = 0; i < n; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("key", scanner.next());
            map.put("value", scanner.next());
            users.add(map);
        }

        int m = scanner.nextInt();

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        for (int i = 0; i < m; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(scanner.next(), scanner.next());
            result.add(map);
        }

        for (Map<String, String> map : users) {
            String key = map.get("key");
            String value = map.get("value");
            for (Map<String, String> map2 : result) {
                if (map2.containsKey(value)) {
                    System.out.println(key + " " + map2.get(value));
                }
            }
        }
    }
}
