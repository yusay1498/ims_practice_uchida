package org.example;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 入力を取得
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // 整数の後の改行を読み取って消費
        String s = scanner.nextLine(); // 文字列の入力

        // 範囲が文字列の長さ内に収まっているか確認
        if (n >= 1 && m <= s.length() && n <= m) {
            for (int i = n; i <= m; i++) {
                System.out.print(s.charAt(i - 1));
            }
        } else {
            System.out.println("Invalid range.");
        }

        scanner.close();
    }
}