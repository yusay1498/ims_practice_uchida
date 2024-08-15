package org.example;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, Object> user = new HashMap<>();
            user.put("nickname", scanner.next());
            user.put("old", Integer.parseInt(scanner.next()));
            user.put("birth", scanner.next());
            user.put("state", scanner.next());
            users.add(user);
        }

        users.sort(Comparator.comparing(user -> (int) user.get("old")));

        for (Map<String, Object> user : users) {
            System.out.println(user.get("nickname") + " " + user.get("old") + " " + user.get("birth") + " " + user.get("state"));
        }
    }
}
