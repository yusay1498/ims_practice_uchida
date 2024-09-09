package org.example;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = scanner.nextInt();
        // List の初期化
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        for (int i = 0; i < n; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put(scanner.next(), scanner.next());
            list.add(map);
        }

        for (Map<String, String> map : list) {
            if (map.containsKey(s)) {
                System.out.println(s + " " + map.get(s));
                break;
            }
        }
    }
}
