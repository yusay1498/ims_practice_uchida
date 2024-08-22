package org.example;

import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReadableCode {
    public static void main(String[] args) {
        //TODO もっとメソッド化して見やすくするべき
        // - Classにして保守性をあげる
        //CHANGED 変数名は全部直した
        int digits = 4;

        //CHANGED 例外処理ができていなかったので追加
        // - その際にargsが存在しない場合はスルーするようにした
        if (args.length > 0) {
            try {
                digits = Integer.parseInt(args[0]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        //CHANGED メソッド化してすっきりさせた
        String answer = createAnswer(digits);

        System.out.println("Secret code is " + digits + "-digits.");

        //CHANGED 一行にした
        System.out.println("*".repeat(digits));

        int turn = 0;

        while (true) {
            //CHANGED 普通にこのあとも文字列でしか扱ってないのでScannerに変えた
            String userAnswer = userAnswerScan();

            //CHANGED retryさせるのにあんなに長くなくていい
            if (!isValidInput(userAnswer, digits)) continue;

            //NOTE 入力が正しければturnが増える
            // - retryの下に配置すればif文とかいらない
            turn++;

            //CHANGED 重複した文字の削除を完結にした
            String blowCheck= deleteDuplicateString(userAnswer);

            //CHANGED 二重ループを完結に書いた
            int hits = Math.toIntExact(IntStream.range(0, digits)
                    .filter(i -> answer.charAt(i) == userAnswer.charAt(i))
                    .count());

            //TODO blowの数え方直す
            //XXX blowが数え方が完全におかしい
            int blows = Math.toIntExact(IntStream.range(0, blowCheck.length())
                    .filter(i -> answer.contains(String.valueOf(blowCheck.charAt(i))))
                    .count()) - hits;

            System.out.println("turn | " + turn);
            System.out.println("hit  | " + hits);
            System.out.println("blow | " + blows);

            if (hits == digits) {
                System.out.println("\nCongrats!");
                return;
            }
        }
    }

    //NOTE 指定された桁数の答えを生成するメソッド
    //CHANGED Randomを使って正しい乱数生成にした
    private static String createAnswer(int digits) {
        StringBuilder answer = new StringBuilder(digits);
        Random rand = new Random();

        for (int i = 0; i < digits; i++) answer.append(rand.nextInt(10));

        return answer.toString();
    }

    //NOTE 入力された文字を読み込む
    private static String userAnswerScan() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    //NOTE 数字以外やdigitsにそぐわない文字数の場合retryさせる
    private static boolean isValidInput(String userAnswer, int digits) {
        if (userAnswer.length() != digits || !userAnswer.matches("^[0-9]+$")) {
            System.out.println("(Please retry)");
            return false;
        }
        return true;
    }

    //NOTE 文字列内に存在する重複した文字を削除する
    private static String deleteDuplicateString(String duplicateString) {
        return duplicateString.chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }
}
