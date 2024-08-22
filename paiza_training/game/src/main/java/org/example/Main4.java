package org.example;

import java.util.*;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int carsNum = scanner.nextInt();
        int eventsNum = scanner.nextInt();
        List<Map<String, Object>> cars = new ArrayList<>();

        for (int i = 0; i < carsNum; i++) {
            Map<String, Object> car = new HashMap<>();
            car.put("carModel", scanner.next());
            car.put("fuel", scanner.nextInt());
            car.put("fuelEfficiency", scanner.nextInt());
            car.put("mileage", 0);
            cars.add(car);
        }

        for (int i = 0; i < eventsNum; i++) {
            int carId1 = scanner.nextInt() - 1;
            String eventName = scanner.next();
            carAction(eventName, cars.get(carId1));
        }

        for (int i = 0; i < carsNum; i++) {
            System.out.println(cars.get(i).get("mileage"));
        }
    }

    static void carAction(String event, Map<String, Object> car) {
        String carModel = car.get("carModel").toString();
        int fuel = (int) car.get("fuel");
        int fuelEfficiency = (int) car.get("fuelEfficiency");
        int mileage = (int) car.get("mileage");

        int fuelEfficiencySquare = (int) Math.pow(fuelEfficiency, 2);
        int fuelEfficiencyFourth = (int) Math.pow(fuelEfficiency, 4);

        if (event.equals("teleport") && fuel > fuelEfficiencySquare) {
            car.put("fuel", fuel - fuelEfficiencySquare);
            car.put("mileage", mileage + fuelEfficiencyFourth);
        } else if ((event.equals("fly") && fuel >= 5) || (event.equals("teleport") && fuel >= 5)) {
            car.put("fuel", fuel - 5);
            if (carModel.equals("supersupersupercar")) {
                car.put("mileage", mileage + 2 * fuelEfficiencySquare);
            } else {
                car.put("mileage", mileage + fuelEfficiencySquare);
            }
        } else if (fuel > 0) {
            car.put("fuel", fuel - 1);
            car.put("mileage", mileage + fuelEfficiency);
        }
    }
}
