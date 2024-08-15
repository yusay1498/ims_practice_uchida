package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        List<Map<String, Object>> users = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Map<String, Object> user = new HashMap<>();
            if (scanner.next().equals("make")) {
                user.put("num", scanner.nextInt());
                user.put("name", scanner.next());
                users.add(user);
            } else if (scanner.next().equals("getname")) {
                getName(scanner.nextInt(), users);
            } else if (scanner.next().equals("getnum")) {
                getNum(scanner.next(), users);
            }
        }
    }

    public void getName(int n , List<Map<String, Object>> users) {
        System.out.println(users.get(n).get("name"));
    }

    private void getNum(int n , List<Map<String, Object>> users) {
        System.out.println(users.get(n).get("num"));
    }
}