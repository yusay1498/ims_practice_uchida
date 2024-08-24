package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 配列の長さ
        int m = scanner.nextInt(); // 入力の数
        int[] arr = new int[n];

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt(); // 範囲の長さ
            int b = scanner.nextInt() - 1; // 範囲の開始位置（0-based index）

            // 範囲が配列を超える可能性を考慮
            boolean allZero = true;
            for (int j = 0; j < a; j++) {
                int index = (b + j) % n; // 配列のインデックスが配列の長さを超えた場合に循環
                if (arr[index] != 0) {
                    allZero = false;
                    break;
                }
            }

            // 全ての要素が 0 であれば、その範囲を 1 にする
            if (allZero) {
                for (int k = 0; k < a; k++) {
                    int index = (b + k) % n; // 配列のインデックスが配列の長さを超えた場合に循環
                    arr[index] = 1;
                }
            }
        }

        // 配列の合計を計算して出力
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        System.out.println(sum);

        scanner.close();
    }
}
