package org.example;

import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String T = scanner.next();
        int m = scanner.nextInt();
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        for (int i = 0; i < m; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(scanner.next(), scanner.next());
            list.add(map);
        }

        for (Map<String, String> map : list) {
            if (map.containsKey(T)) {
                System.out.println(map.get(T));
                break;
            }
        }
    }
}
