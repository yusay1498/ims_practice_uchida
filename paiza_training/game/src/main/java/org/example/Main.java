package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pointsNum = scanner.nextInt();
        int movesNum = scanner.nextInt();
        int startPoint = scanner.nextInt();
        List<Map<String, Object>> points = new ArrayList<>();

        for (int i = 0; i < pointsNum; i++) {
            Map<String, Object> point = new HashMap<>();
            point.put("alphabet", scanner.next());
            point.put("destination1", scanner.nextInt());
            point.put("destination2", scanner.nextInt());
            points.add(point);
        }

        int currentLocation = startPoint - 1;
        System.out.print(points.get(currentLocation).get("alphabet"));
        for (int i = 0; i < movesNum; i++) {
            int move = scanner.nextInt();
            if (move == 1) {
                currentLocation = (int) points.get(currentLocation).get("destination1") - 1;
            } else if (move == 2) {
                currentLocation = (int) points.get(currentLocation).get("destination2") - 1;
            }
            System.out.print(points.get(currentLocation).get("alphabet"));
        }
    }
}