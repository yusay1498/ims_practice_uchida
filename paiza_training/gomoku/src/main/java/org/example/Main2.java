package org.example;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int counterO = 0;
        int counterX = 0;

        // 入力文字列をループしてカウント
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == 'O') {
                counterO++;
            } else if (ch == 'X') {
                counterX++;
            }
        }

        // 出力条件の判定
        if (counterO == 5) {
            System.out.println("O");
        } else if (counterX == 5) {
            System.out.println("X");
        } else {
            System.out.println("D");
        }
    }
}
