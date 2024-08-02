package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Path dictionaryPath = Path.of("dictionary.txt");

        Set<String> cpuDictionary = new LinkedHashSet<>();
        Set<String> usedWords = new LinkedHashSet<>();

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

        while (!cpuDictionary.isEmpty()) {
            int judge;
            String userWord = scannerWord();

            if (userWord.endsWith("ん")) {

                judge = 2;
            } else if (usedWords.stream()
                    .anyMatch(user -> user.contains(userWord))) {
                //ユーザーが既出の単語を使用した場合
                judge = 4;
            } else if (cpuDictionary.stream()
                    .anyMatch(cpu -> cpu.startsWith(userWord.substring(userWord.length() - 1)))) {
                judge = 0;
            } else {
                //CPUの単語が尽きた場合
                judge = 3;
            }

            usedWords.add(userWord);

            if (cpuDictionary.stream()
                    .equals(userWord)) {
                cpuDictionary.remove(userWord);
            }

            judgment(judge, userWord, null);
            if (judge > 0) {
                break;
            }

            String cpuWord = "";

            for (String word : cpuDictionary) {
                if (word.startsWith(userWord.substring(userWord.length() - 1))) {
                    cpuWord = word;
                    break;
                }
            }

            if (cpuWord != "") {
                cpuDictionary.remove(cpuWord);
            }

            if (cpuWord.endsWith("ん")) {
                //CPUが「ん」で終わる単語を選択した場合
                judge = 1;
            } else {
                judge = -1;
            }

            judgment(judge, userWord, cpuWord);
            if (judge > 0) {
                break;
            }
        }
        if (cpuDictionary.isEmpty()) {
            System.out.println("CPU: これ以上思いつきません");
            System.out.println("CPU: 参りました");
        }
    }

    private static String scannerWord() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void judgment(int judge, String userWord, String cpuWord) {
        if (judge == -1) {
            System.out.println("CPU: " + cpuWord);
        } else if (judge == 0) {
            System.out.println("> " + userWord);
        } else if (judge == 1) {
            System.out.println("CPU: " + cpuWord);
            System.out.println("CPU: 参りました");
        } else if (judge == 2) {
            System.out.println("CPU: 「ん」がつきました");
            System.out.println("CPU: わたしの勝ちです");
        } else if (judge == 4) {
            System.out.println("CPU: 「" + userWord + "」は既出です");
            System.out.println("CPU: わたしの勝ちです");
        } else if (judge == 3) {
            System.out.println("CPU: " + userWord.substring(userWord.length() - 1) + "・・・");
            System.out.println("CPU: 参りました");
        }
    }
}