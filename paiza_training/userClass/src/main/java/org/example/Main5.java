package org.example;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int usersNum = scanner.nextInt();
        int orderNum = scanner.nextInt();
        int leavingUserNum = 0;
        List<Map<String, Integer>> users = new ArrayList<>();

        // ユーザーの情報を読み取る
        for (int i = 0; i < usersNum; i++) {
            Map<String, Integer> user = new HashMap<>();
            user.put("age", scanner.nextInt());
            user.put("sum", 0);
            user.put("alcoholCount", 0);
            users.add(user);
        }

        // 注文の処理
        for (int i = 0; i < orderNum; i++) {
            int userId = scanner.nextInt() - 1;
            if (userId < 0 || userId >= usersNum) {
                System.out.println("Invalid userId: " + (userId + 1));
                continue;
            }
            String command = scanner.next();
            if (command.equals("0")) {
                Map<String, Integer> user = users.get(userId);
                if (user.get("age") >= 20) {
                    user.put("sum", user.get("sum") + 500);
                    user.put("alcoholCount", 1);
                }
            } else if (command.equals("A")) {
                System.out.println(users.get(userId).get("sum"));
                leavingUserNum++;
            } else {
                int amount = scanner.nextInt();  // ここで値を1回だけ取得
                Map<String, Integer> user = users.get(userId);
                if (user.get("alcoholCount") == 0) {
                    if (command.equals("food") || command.equals("softdrink")) {
                        user.put("sum", user.get("sum") + amount);
                    } else if (command.equals("alcohol") && user.get("age") >= 20) {
                        user.put("sum", user.get("sum") + amount);
                        user.put("alcoholCount", 1);
                    }
                } else {
                    if (command.equals("food")) {
                        user.put("sum", user.get("sum") + amount - 200);
                    } else if (command.equals("softdrink")) {
                        user.put("sum", user.get("sum") + amount);
                    } else if (command.equals("alcohol") && user.get("age") >= 20) {
                        user.put("sum", user.get("sum") + amount);
                    }
                }
            }
        }

        // 結果の出力
        System.out.println(leavingUserNum);
    }
}