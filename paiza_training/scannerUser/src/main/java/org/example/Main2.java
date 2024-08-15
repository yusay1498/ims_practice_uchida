package org.example;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        List<Map<String, String>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, String> user = new HashMap<>();
            user.put("nickname", scanner.nextLine());
            user.put("old", scanner.nextLine());
            user.put("birth", scanner.nextLine());
            user.put("state", scanner.nextLine());
            users.add(user);
        }
        for (Map<String, String> user : users) {
            if (user.get("old").equals(String.valueOf(age))) {
                System.out.println("User{");
                System.out.println("nickname : " + user.get("nickname"));
                System.out.println("old : " + user.get("old"));
                System.out.println("birth : " + user.get("birth"));
                System.out.println("state : " + user.get("state"));
                System.out.println("}");
            }
        }
    }
}
