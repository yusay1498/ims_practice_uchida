package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        List<Map<String, String>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, String> user = new HashMap<>();
            user.put("nickname", scanner.next());
            user.put("old", scanner.next());
            user.put("birth", scanner.next());
            user.put("state", scanner.next());
            users.add(user);
        }
        for (Map<String, String> user : users) {
            System.out.println("User{");
            System.out.println("nickname : " + user.get("nickname"));
            System.out.println("old : " + user.get("old"));
            System.out.println("birth : " + user.get("birth"));
            System.out.println("state : " + user.get("state"));
            System.out.println("}");
        }
    }
}