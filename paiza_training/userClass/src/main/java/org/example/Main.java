package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String command = scanner.next();
            if (command.equals("make")) {
                Map<String, Object> user = new HashMap<>();
                user.put("num", scanner.nextInt());
                user.put("name", scanner.next());
                users.add(user);
            } else if (command.equals("getname")) {
                getNameByNum(scanner.nextInt(), users);
            } else if (command.equals("getnum")) {
                getNum(scanner.nextInt(), users);
            }
        }
    }

    public static void getNameByNum(int n , List<Map<String, Object>> users) {
        System.out.println(users.get(n).get("name"));
    }

    public static void getNum(int n , List<Map<String, Object>> users) {
        System.out.println(users.get(n).get("num"));
    }
}
