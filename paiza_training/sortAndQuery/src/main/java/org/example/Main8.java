package org.example;

import java.util.*;

public class Main8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 他の生徒の人数
        int K = sc.nextInt(); // イベントの数
        int P = sc.nextInt(); // paiza君の身長

        int smaller = 0;

        // 他の生徒の身長を読み取り、paiza君より小さい身長の生徒の数をカウント
        for (int i = 0; i < N; i++) {
            int A = sc.nextInt();
            if (A < P) {
                smaller++;
            }
        }

        // イベント処理
        for (int i = 0; i < K; i++) {
            String event = sc.next();
            if (event.equals("sorting")) {
                // paiza君の順位を出力
                System.out.println(smaller + 1);
            } else if (event.equals("join")) {
                int num = sc.nextInt();
                if (num < P) {
                    smaller++;
                }
            }
        }

        sc.close();
    }
}
