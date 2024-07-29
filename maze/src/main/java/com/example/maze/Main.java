package com.example.maze;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // ダメポイント1
        // wとhが奇数でなくてはならない
        int w = 41;
        int h = 21;

        if (args.length >= 1) {
            try {
                w = Integer.parseInt(args[0]);
            } catch (Exception e) {
                // NOP
            }
        }

        if (args.length >= 2) {
            try {
                h = Integer.parseInt(args[1]);
            } catch (Exception e) {
                // NOP
            }
        }

        System.out.println("Width : " + w);
        System.out.println("Height: " + h);
        System.out.println();

        // TODO: (1) 迷路作成
        int[][] num;
        num = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h - 1) {
                    num[i][j] = 1;
                } else {
                    if (j == w - 1) {
                        num[i][j] = 1;
                    } else if (j == 0 || (i % 2 == 0 && j % 2 == 0)) {
                        num[i][j] = 1;
                    } else {
                        num[i][j] = 0;
                    }
                }
            }
        }

        // ダメポイント2
        // たまに全然面白くない迷路ができる
        for (int i = 1; i < h - 1; i++) {
            for (int j = 1; j < w - 1; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    Random rand = new Random();
                    int n = rand.nextInt(3);
                    if (n == 0) {
                        if (num[i][j - 1] == 1) {
                            num[i + 1][j] = 1;
                        } else {
                            num[i][j - 1] = 1;
                        }
                    } else if (n == 1 && num[i - 1][j] == 0) {
                        if (num[i - 1][j] == 1) {
                            num[i][j + 1] = 1;
                        } else {
                            num[i - 1][j] = 1;
                        }
                    } else if (n == 2) {
                        num[i][j + 1] = 1;
                    } else {
                        num[i + 1][j] = 1;
                    }
                }
            }
        }

        num[1][0] = 0;
        num[h - 2][w - 1] = 0;

        // TODO: (2) ルート作成


        // 迷路・ルート描画に使用する文字を定義
        String WALL = "██";
        String PASSAGE = "  ";
        String ROUTE = "\u001B[44m  \u001B[0m";

        // TODO: (1) 迷路描画 -> (2) 迷路＆ルート描画
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (num[i][j] == 0) {
                    if (i == h - 2 && j == w - 1) {
                        System.out.println(PASSAGE);
                    } else {
                        System.out.print(PASSAGE);
                    }
                } else {
                    if (j == w - 1) {
                        System.out.println(WALL);
                    } else {
                        System.out.print(WALL);
                    }
                }
            }
        }
    }
}
