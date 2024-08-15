package org.example;

import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, Object> user = new HashMap<>();
            String[] userInfo = scanner.nextLine().split(" ");
            user.put("nickname", userInfo[0]);
            user.put("old", Integer.parseInt(userInfo[1]));
            user.put("birth", userInfo[2]);
            user.put("state", userInfo[3]);
            users.add(user);
        }

        for (int i = 0; i < M; i++) {
            String[] updateInfo = scanner.nextLine().split(" ");
            int K = Integer.parseInt(updateInfo[0]) - 1;
            String newName = updateInfo[1];
            users.get(K).put("nickname", newName);
        }

        for (Map<String, Object> user : users) {
            System.out.println(user.get("nickname") + " " + user.get("old") + " " + user.get("birth") + " " + user.get("state"));
        }
    }
}