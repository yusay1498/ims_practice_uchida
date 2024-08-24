package org.example;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 整数の入力を取得
        int n = scanner.nextInt();
        scanner.nextLine(); // 改行を消費

        // 文字列の入力を取得
        String input = scanner.next();

        // 範囲チェックと出力
        if (n > 0 && n + 1 <= input.length()) {
            // n - 1 は 0-based index なので、n が文字列の長さより小さいか確認
            System.out.print(input.charAt(n - 1) + " ");
            if (n < input.length()) {
                System.out.println(input.charAt(n));
            }
        }

        scanner.close();
    }
}