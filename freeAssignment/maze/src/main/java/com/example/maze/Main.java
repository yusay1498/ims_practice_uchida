package com.example.maze;

import java.util.Random;
import java.util.Stack;

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
        int[][] maze = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h - 1) {
                    maze[i][j] = 1;
                } else {
                    if (j == w - 1) {
                        maze[i][j] = 1;
                    } else if (j == 0 || (i % 2 == 0 && j % 2 == 0)) {
                        maze[i][j] = 1;
                    } else {
                        maze[i][j] = 0;
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
                        if (maze[i][j - 1] == 1) {
                            maze[i + 1][j] = 1;
                        } else {
                            maze[i][j - 1] = 1;
                        }
                    } else if (n == 1 && maze[i - 1][j] == 0) {
                        if (maze[i - 1][j] == 1) {
                            maze[i][j + 1] = 1;
                        } else {
                            maze[i - 1][j] = 1;
                        }
                    } else if (n == 2) {
                        maze[i][j + 1] = 1;
                    } else {
                        maze[i + 1][j] = 1;
                    }
                }
            }
        }

        maze[1][0] = 0;
        maze[h - 2][w - 1] = 3;

        // TODO: (2) ルート作成

        maze = solveMaze(maze, 1, 0);

        // 迷路・ルート描画に使用する文字を定義
        String WALL = "██";
        String PASSAGE = "  ";
        String ROUTE = "\u001B[44m  \u001B[0m";

        // TODO: (1) 迷路描画 -> (2) 迷路＆ルート描画
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (maze[i][j] == 0) {
                    if (i == h - 2 && j == w - 1) {
                        System.out.println(PASSAGE);
                    } else {
                        System.out.print(PASSAGE);
                    }
                } else if (maze[i][j] == 1) {
                    if (j == w - 1) {
                        System.out.println(WALL);
                    } else {
                        System.out.print(WALL);
                    }
                } else {
                    if (j == w - 1) {
                        System.out.println(ROUTE);
                    } else {
                        System.out.print(ROUTE);
                    }
                }
            }
        }
    }

    private static int[][] solveMaze(int[][] maze, int startI, int startJ) {
        int[][] solvedMaze = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            System.arraycopy(maze[i], 0, solvedMaze[i], 0, maze[i].length);
        }

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{startI, startJ});

        while (!stack.isEmpty()) {
            int[] pos = stack.pop();
            int i = pos[0], j = pos[1];

            if (solvedMaze[i][j] == 3) {
                solvedMaze[i][j] = 2;
                return solvedMaze;
            }

            if (solvedMaze[i][j] != 2) {
                solvedMaze[i][j] = 2;

                if (i > 0 && (solvedMaze[i - 1][j] == 0 || solvedMaze[i - 1][j] == 3)) stack.push(new int[]{i - 1, j});
                if (j > 0 && (solvedMaze[i][j - 1] == 0 || solvedMaze[i][j - 1] == 3)) stack.push(new int[]{i, j - 1});
                if (i < maze.length - 1 && (solvedMaze[i + 1][j] == 0 || solvedMaze[i + 1][j] == 3)) stack.push(new int[]{i + 1, j});
                if (j < maze[0].length - 1 && (solvedMaze[i][j + 1] == 0 || solvedMaze[i][j + 1] == 3)) stack.push(new int[]{i, j + 1});
            }
        }
        return solvedMaze;
    }
}
