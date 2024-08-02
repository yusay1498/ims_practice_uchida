package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> answerNumber = new LinkedList<>();
        int turnCount = 0;

        while (true) {
            Random rand = new Random();
            //0~9で作ったけど通常の難易度なら0~5にしたほうがいい
            int addNumber = rand.nextInt(9);
            //難易度を上げるために重複ありにするならここを直す
            if (answerNumber.stream()
                    .noneMatch(ansNum -> ansNum == addNumber)) {
                answerNumber.add(addNumber);
            }
            if (answerNumber.size() == 4) break;
        }

        while (true) {
            String userAnswerString = scannerNumber();
            List<Integer> userAnswerNumber = new LinkedList<>();
            int hitPoints = 0;
            int blowPoints = 0;
            turnCount++;

            for (int i = 0; i < userAnswerString.length(); i++) {
                userAnswerNumber.add(Integer.parseInt(userAnswerString.charAt(i) + ""));
            }

            for (int confirmAnswer : userAnswerNumber) {
                if (answerNumber.stream()
                        .anyMatch(ansNum -> ansNum == confirmAnswer)) hitPoints++;
            }

            for (int count = 0; count < answerNumber.size(); count++) {
                if (answerNumber.get(count).equals(userAnswerNumber.get(count))) {
                    blowPoints++;
                    hitPoints--;
                }
            }

            System.out.println("CPU: Hit=" + hitPoints + "Blow=" + blowPoints);

            if (blowPoints == 4) {
                System.out.println("CPU: クリアまでに" + turnCount + "ターンかかりました");
                break;
            }
        }
    }

    private static String scannerNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}