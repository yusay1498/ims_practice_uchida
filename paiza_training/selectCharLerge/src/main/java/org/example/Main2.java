package org.example;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 入力の読み取り
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine(); // 改行を消費
        String input = scanner.nextLine();

        // StringBuilder を使用して結果を構築
        StringBuilder builder = new StringBuilder();

        // 範囲チェックと文字列の変換
        for (int i = 0; i < input.length(); i++) {
            // インデックスが範囲内か確認
            if (i >= n - 1 && i < m) {
                builder.append(Character.toUpperCase(input.charAt(i)));
            } else {
                builder.append(input.charAt(i));
            }
        }

        // 結果を出力
        System.out.println(builder.toString());

        scanner.close();
    }
}
