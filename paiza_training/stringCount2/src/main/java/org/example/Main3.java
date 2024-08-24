package org.example;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1行目の入力を読み取る
        String s = scanner.nextLine();
        // 2行目の入力を読み取る
        String t = scanner.nextLine();

        // 出現回数をカウントするための変数
        int count = 0;
        int sLength = s.length();
        int tLength = t.length();

        // t 内で s の出現を探す
        for (int i = 0; i <= tLength - sLength; i++) {
            // t の i から s.length() までが s と一致するかチェック
            if (t.substring(i, i + sLength).equals(s)) {
                count++;
            }
        }

        // 出現回数を出力
        System.out.println(count);

        scanner.close();
    }
}
