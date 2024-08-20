package org.example;

import java.util.*;

//　入力
//　3 6
//　10 1 1 2 2 3 3
//　10 0 0 6 1 7 2
//　10 0 0 7 5 8 3
//　1 1 2 2
//　1 2 3 2
//　1 3 2 3
//　2 2 3 1
//　2 3 3 1
//　1 2 3 2

//  出力
//  2

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int usersNum = scanner.nextInt();
        int eventsNum = scanner.nextInt();
        List<Map<String, Integer>> users = new ArrayList<>();

        for (int i = 0; i < usersNum; i++) {
            Map<String, Integer> user = new HashMap<>();
            user.put("hitPoint", scanner.nextInt());
            user.put("skill1", scanner.nextInt());
            user.put("attackPoint1", scanner.nextInt());
            user.put("skill2", scanner.nextInt());
            user.put("attackPoint2", scanner.nextInt());
            user.put("skill3", scanner.nextInt());
            user.put("attackPoint3", scanner.nextInt());
            users.add(user);
        }

        for (int i = 0; i < eventsNum; i++) {
            int userId1 = scanner.nextInt() - 1;
            String eventName1 = scanner.next();
            String userSkill1 = skill(eventName1);
            int userId2 = scanner.nextInt() - 1;
            String eventName2 = scanner.next();
            String userSkill2 = skill(eventName2);
            if (users.get(userId1).get("hitPoint") <= 0 || users.get(userId2).get("hitPoint") <= 0) {
                if (users.get(userId1).get(userSkill1) == 0) {
                    buff(users, userId1);
                    attack(users, userId2, userId1, userSkill2);
                }
                if (users.get(userId2).get(userSkill2) == 0) {
                    buff(users, userId2);
                    attack(users, userId1, userId2, userSkill1);
                }
                if (users.get(userId1).get(userSkill1) < users.get(userId2).get(userSkill2)) {
                    attack(users, userId1, userId2, userSkill1);
                } else if (users.get(userId2).get(userSkill2) < users.get(userId1).get(userSkill1)) {
                    attack(users, userId2, userId1, userSkill2);
                }
            }
        }

        int count = 0;
        for (Map<String, Integer> user : users) {
            if (user.get("hitPoint") > 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static String skill(String eventName) {
        switch (eventName) {
            case "1" -> {
                return "skill1";
            }
            case "2" -> {
                return "skill2";
            }
            case "3" -> {
                return "skill3";
            }
        }
        return null;
    }

    private static void buff(List<Map<String, Integer>> users, Integer userId) {
        users.get(userId).put("skill1", users.get(userId).get("skill1") - 3);
        users.get(userId).put("attackPoint1", users.get(userId).get("attackPoint1") + 5);
        users.get(userId).put("skill2", users.get(userId).get("skill2") - 3);
        users.get(userId).put("attackPoint2", users.get(userId).get("attackPoint2") + 5);
        users.get(userId).put("skill3", users.get(userId).get("skill3") - 3);
        users.get(userId).put("attackPoint3", users.get(userId).get("attackPoint3") + 5);
        if (users.get(userId).get("skill1") < 1) {
            users.get(userId).put("skill1", 1);
        } else if (users.get(userId).get("skill2") < 1) {
            users.get(userId).put("skill2", 1);
        } else if (users.get(userId).get("skill3") < 1) {
            users.get(userId).put("skill3", 1);
        }
    }

    private static void attack(List<Map<String, Integer>> users, Integer attackUser, Integer defenseUser, String skill) {
        switch (skill) {
            case "skill1" ->
                    users.get(defenseUser).put("hitPoint", users.get(defenseUser).get("hitPoint") - users.get(attackUser).get("attackPoint1"));
            case "skill2" ->
                    users.get(defenseUser).put("hitPoint", users.get(defenseUser).get("hitPoint") - users.get(attackUser).get("attackPoint2"));
            case "skill3" ->
                    users.get(defenseUser).put("hitPoint", users.get(defenseUser).get("hitPoint") - users.get(attackUser).get("attackPoint3"));
        }
    }
}