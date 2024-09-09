package org.example;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 5;
        String[] matrix = new String[n];
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            matrix[i] = input;
        }

        int counterO;
        int counterX;


        // 左上から右下への対角線のチェック
        counterO = 0;
        counterX = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[i].charAt(i) == 'O') {
                counterO++;
            } else if (matrix[i].charAt(i) == 'X') {
                counterX++;
            }
        }
        if (counterO == 5) {
            System.out.println("O");
            return;
        }
        if (counterX == 5) {
            System.out.println("X");
            return;
        }

        // 右上から左下への対角線のチェック
        counterO = 0;
        counterX = 0;
        for (int i = 0; i < n; i++) {
            if (matrix[i].charAt(n - 1 - i) == 'O') {
                counterO++;
            } else if (matrix[i].charAt(n - 1 - i) == 'X') {
                counterX++;
            }
        }
        if (counterO == 5) {
            System.out.println("O");
            return;
        }
        if (counterX == 5) {
            System.out.println("X");
            return;
        }

        // 出力条件の判定
        System.out.println("D");
    }
}
