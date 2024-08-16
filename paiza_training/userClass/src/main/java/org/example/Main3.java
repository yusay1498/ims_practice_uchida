package org.example;

import java.util.*;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int usersNum = scanner.nextInt();
        int orderNum = scanner.nextInt();
        List<Map<String, Integer>> users = new ArrayList<>();
        for (int i = 0; i < usersNum; i++) {
            Map<String, Integer> user = new HashMap<>();
            user.put("age", scanner.nextInt());
            user.put("sum", 0);
            user.put("alcoholCount", 0);
            users.add(user);
        }

        for (int i = 0; i < orderNum; i++) {
            int userId = scanner.nextInt() - 1;
            String command = scanner.next();
            if (users.get(userId).get("alcoholCount") == 0) {
                if (command.equals("food")) {
                    users.get(userId).put("sum", users.get(userId).get("sum") + scanner.nextInt());
                } else if (command.equals("softdrink")) {
                    users.get(userId).put("sum", users.get(userId).get("sum") + scanner.nextInt());
                } else if (command.equals("alcohol") && users.get(userId).get("age") >= 20) {
                    users.get(userId).put("alcoholCount", 1);
                    users.get(userId).put("sum", users.get(userId).get("sum") + scanner.nextInt());
                }
            } else {
                if (command.equals("food")) {
                    users.get(userId).put("sum", users.get(userId).get("sum") + scanner.nextInt() - 200);
                } else if (command.equals("softdrink")) {
                    users.get(userId).put("sum", users.get(userId).get("sum") + scanner.nextInt());
                } else if (command.equals("alcohol") && users.get(userId).get("age") >= 20) {
                    users.get(userId).put("sum", users.get(userId).get("sum") + scanner.nextInt());
                }
            }
        }

        for (Map<String, Integer> user : users) {
            System.out.println(user.get("sum"));
        }
    }
}
