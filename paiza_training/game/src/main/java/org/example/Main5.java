package org.example;

import java.util.*;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int coordinateXMax = scanner.nextInt();
        int coordinateYMax = scanner.nextInt();
        int robotNum = scanner.nextInt();
        int eventNum = scanner.nextInt();
        int itemNum = 10;
        List<Map<String, Integer>> itemPlace = new ArrayList<>();
        List<Map<String, Integer>> robots = new ArrayList<>();

        for (int i = 0; i < itemNum; i++) {
            Map<String, Integer> item = new HashMap<>();
            item.put("coordinateX", scanner.nextInt());
            item.put("coordinateY", scanner.nextInt());
            itemPlace.add(item);
        }

        for (int i = 0; i < robotNum; i++) {
            Map<String, Integer> robot = new HashMap<>();
            robot.put("coordinateX", scanner.nextInt());
            robot.put("coordinateY", scanner.nextInt());
            robot.put("level", scanner.nextInt());
            robots.add(robot);
        }

        for (int i = 0; i < eventNum; i++) {
            int robotId = scanner.nextInt() - 1;
            String direction = scanner.next();
            action(robots.get(robotId), itemPlace, direction);
        }

        for (Map<String, Integer> robot : robots) {
            System.out.println(robot.get("coordinateX") + " " + robot.get("coordinateY") + " " + robot.get("level"));
        }
    }

    static void action(Map<String, Integer> robot, List<Map<String, Integer>> itemPlace, String direction) {
        int coordinateX = robot.get("coordinateX");
        int coordinateY = robot.get("coordinateY");
        int level = robot.get("level");
        int working = working(level);
        switch (direction) {
            case "W" -> robot.put("coordinateX", coordinateX - working);
            case "S" -> robot.put("coordinateY", coordinateY + working);
            case "E" -> robot.put("coordinateX", coordinateX + working);
            case "N" -> robot.put("coordinateY", coordinateY - working);
        }
        for (Map<String, Integer> item : itemPlace) {
            if (Objects.equals(item.get("coordinateX"), robot.get("coordinateX")) && Objects.equals(item.get("coordinateY"), robot.get("coordinateY"))) {
                robot.put("level", level + 1);
            }
        }
    }

    static int working(int level) {
        if (level == 1) {
            return 1;
        } else if (level == 2) {
            return  2;
        } else if (level == 3) {
            return  5;
        } else if (level == 4) {
            return  10;
        }
        return 0;
    }
}
