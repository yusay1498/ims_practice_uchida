package org.example;

import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String U = scanner.next();
        int n = scanner.nextInt();
        List<Map<String, String>> users = new ArrayList<Map<String, String>>();

        for (int i = 0; i < n; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(scanner.next(), scanner.next());
            users.add(map);
        }

        int m = scanner.nextInt();

        List<Map<String, String>> result = new ArrayList<Map<String, String>>();

        for (int i = 0; i < m; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(scanner.next(), scanner.next());
            result.add(map);
        }

        String blood = null;
        for (Map<String, String> map : users) {
            if (map.containsKey(U)) {
                blood = map.get(U);
                break;
            }
        }

        for (Map<String, String> map : result) {
            if (map.containsKey(blood)) {
                System.out.println(map.get(blood));
                break;
            }
        }
    }
}
