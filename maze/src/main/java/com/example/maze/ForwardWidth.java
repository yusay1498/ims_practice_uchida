package com.example.maze;

public class ForwardWidth {
    private int i;
    private int j;
    public int[][] forwardWidth(int[][] maze, int i, int j) {
        while (maze[i][j + 1] != 1 && maze[i][j] != 3 && maze[i - 1][j] == 1) {
            maze[i][j] = 2;
            j += 1;
            this.i = i;
            this.j = j;
        }
        return maze;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
