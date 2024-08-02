package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path dictionaryPath = Path.of("dictionary.txt");
        List<String> cpuDictionary = new LinkedList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(dictionaryPath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                cpuDictionary.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("小文字で5文字の英単語を当てるゲームです");

        int turnCount = 0;
        Random rand = new Random();
        String answerWord = cpuDictionary.get(rand.nextInt(cpuDictionary.size()));

        System.out.println(answerWord);

        while (true) {
            List<String> answerWordList = new LinkedList<>();
            for (int i = 0; i < answerWord.length(); i++) {
                answerWordList.add(answerWord.substring(i, i + 1));
            }
            String userAnswerString = scannerNumber();
            List<String> userAnswerList = new LinkedList<>();
            for (int i = 0; i < userAnswerString.length(); i++) {
                userAnswerList.add(userAnswerString.substring(i, i + 1));
            }
            turnCount++;
            int blowPoints = 0;

            for (int confirmAnswer = 0; confirmAnswer < answerWord.length(); confirmAnswer++) {
                if (answerWord.charAt(confirmAnswer) == userAnswerString.charAt(confirmAnswer)) {
                    userAnswerList.set(confirmAnswer, "[" + userAnswerList.get(confirmAnswer) + "]");
                    blowPoints++;
                }
            }

            for (int confirmAnswer = 0; confirmAnswer < userAnswerList.size(); confirmAnswer++) {
                String strConfirm = userAnswerList.get(confirmAnswer);
                for (int answerIndexNum = 0; answerIndexNum < answerWordList.size(); answerIndexNum++) {
                    if (strConfirm.equals(answerWordList.get(answerIndexNum))) {
                        userAnswerList.set(confirmAnswer, "(" + userAnswerList.get(confirmAnswer) + ")");
                    }
                }
            }

            System.out.print("CPU: ");
            for (int i = 0; i < userAnswerList.size(); i++) {
                System.out.print(userAnswerList.get(i));
            }

            System.out.println("\n");

            if (blowPoints == answerWordList.size()) {
                System.out.println("CPU: Congrats!");
                break;
            }
        }
    }

    private static String scannerNumber() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}