package org.example;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 5;
        String[] matrix = new String[n];
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            matrix[i] = input;
        }

        int counterO = 0;
        int counterX = 0;
        boolean judge = false;

        // 入力文字列をループしてカウント
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                if (matrix[i].charAt(j) == 'O') {
                    counterO++;
                } else if (matrix[i].charAt(j) == 'X') {
                    counterX++;
                }
            }
            if (counterO == 5 || counterX == 5) break;
            else {
                counterX = 0;
                counterO = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrix[i].length(); j++) {
                if (matrix[j].charAt(i) == 'O') {
                    counterO++;
                } else if (matrix[j].charAt(i) == 'X') {
                    counterX++;
                }
            }
            if (counterO == 5 || counterX == 5) break;
            else {
                counterX = 0;
                counterO = 0;
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
