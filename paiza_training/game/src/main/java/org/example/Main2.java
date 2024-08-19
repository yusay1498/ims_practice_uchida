package org.example;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int usersNum = scanner.nextInt();
        int eventsNum = scanner.nextInt();
        List<Map<String, Integer>> users = new ArrayList<>();

        for (int i = 0; i < usersNum; i++) {
            Map<String, Integer> user = new HashMap<>();
            user.put("level", scanner.nextInt());
            user.put("hitPoint", scanner.nextInt());
            user.put("attackPoint", scanner.nextInt());
            user.put("defensePoint", scanner.nextInt());
            user.put("speed", scanner.nextInt());
            user.put("cleverPoint", scanner.nextInt());
            user.put("fortunePoint", scanner.nextInt());
            users.add(user);
        }

        for (int i = 0; i < eventsNum; i++) {
            int userId = scanner.nextInt() - 1;
            String eventName = scanner.next();
            if (eventName.equals("levelup")) {
                users.get(userId).put("level", users.get(userId).get("level") + 1);
                users.get(userId).put("hitPoint", users.get(userId).get("hitPoint") + scanner.nextInt());
                users.get(userId).put("attackPoint", users.get(userId).get("attackPoint") + scanner.nextInt());
                users.get(userId).put("defensePoint", users.get(userId).get("defensePoint") + scanner.nextInt());
                users.get(userId).put("speed", users.get(userId).get("speed") + scanner.nextInt());
                users.get(userId).put("cleverPoint", users.get(userId).get("cleverPoint") + scanner.nextInt());
                users.get(userId).put("fortunePoint", users.get(userId).get("fortunePoint") + scanner.nextInt());
            } else if (eventName.equals("muscle_training")) {
                users.get(userId).put("hitPoint", users.get(userId).get("hitPoint") + scanner.nextInt());
                users.get(userId).put("attackPoint", users.get(userId).get("attackPoint") + scanner.nextInt());
            } else if (eventName.equals("running")) {
                users.get(userId).put("defensePoint", users.get(userId).get("defensePoint") + scanner.nextInt());
                users.get(userId).put("speed", users.get(userId).get("speed") + scanner.nextInt());
            } else if (eventName.equals("study")) {
                users.get(userId).put("cleverPoint", users.get(userId).get("cleverPoint") + scanner.nextInt());
            } else if (eventName.equals("pray")) {
                users.get(userId).put("fortunePoint", users.get(userId).get("fortunePoint") + scanner.nextInt());
            }

            for (Map<String, Integer> user : users) {
                System.out.print(user.get("level") + " ");
                System.out.print(user.get("hitPoint") + " ");
                System.out.print(user.get("attackPoint" + " "));
                System.out.print(user.get("defensePoint" + " "));
                System.out.print(user.get("speed" + " "));
                System.out.print(user.get("cleverPoint" + " "));
                System.out.print(user.get("fortunePoint"));
            }
        }
    }
}