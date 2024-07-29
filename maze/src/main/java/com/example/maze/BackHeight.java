package com.example.maze;

public class BackHeight {
    private int i;
    private int j;
    public int[][] backHeight(int[][] maze, int i, int j) {
        while (maze[i][j] != 1 && maze[i][j] != 3 && maze[i][j + 1] == 1) {
            maze[i][j] = 2;
            i -= 1;
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

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
