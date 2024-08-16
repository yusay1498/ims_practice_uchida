package org.example;

import java.util.*;

public class Main2 {
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
            } else if (command.equals("change_name")) {
                changeName(users, scanner.nextInt(), scanner.next());
            } else if (command.equals("change_num")) {
                changeNum(users, scanner.nextInt(), scanner.nextInt());
            }
        }
    }

    public static void getNameByNum(int n , List<Map<String, Object>> users) {
        System.out.println(users.get(n - 1).get("name"));
    }

    public static void getNum(int n , List<Map<String, Object>> users) {
        System.out.println(users.get(n - 1).get("num"));
    }

    public static void changeName(List<Map<String, Object>> users, int num, String name) {
        users.get(num -1).put("name", name);
    }

    public static void changeNum(List<Map<String, Object>> users, int num, int newNum) {
        users.get(num -1).put("num", newNum);
    }
}
