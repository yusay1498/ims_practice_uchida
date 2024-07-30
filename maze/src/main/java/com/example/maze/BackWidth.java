package com.example.maze;

public class BackWidth {
    private int i;
    private int j;
    public int[][] backWidth(int[][] maze, int i, int j) {
        while (maze[i][j - 1] != 1 && maze[i][j] != 3 && maze[i + 1][j] == 1) {
            maze[i][j] = 2;
            j -= 1;
            setI(i);
            setJ(j);
        }
        return maze;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
