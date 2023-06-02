package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int numOfOpenSites;
    private WeightedQuickUnionUF wqu;
    private int finalPos;
    private int N;
    private static final int CLOSE_SITE = -1;
    private static final int OPEN_SITE = 0;
    private static final int FULL_SITE = 1;
    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    private static final int ROW_IDX = 0;
    private static final int COL_IDX = 1;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Can not create percolation with N <= 0");
        }
        this.N = N;
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = CLOSE_SITE;
            }
        }
        wqu = new WeightedQuickUnionUF(N * N);
        for (int i = 1; i < N; i++) {
            wqu.union(0, i);
        }

        finalPos = getArrayIndex(N - 1, N - 1);
        for (int i = 0; i < N - 1; i++) {
            wqu.union(finalPos, getArrayIndex(N - 1, i));
        }
        this.numOfOpenSites = 0;
    }

    private int getArrayIndex(int row, int col) {
        return row * N + col;
    }

    private boolean checkIndex(int row, int col) {
        boolean checkRow = row >= 0 && row <= (N - 1);
        boolean checkCol = col >= 0 && col <= (N - 1);
        return checkCol && checkRow;
    }

    public boolean isOpen(int row, int col) {
        if (!checkIndex(row, col)) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1");
        }
        return grid[row][col] != CLOSE_SITE;
    }

    public boolean isFull(int row, int col) {
        if (!checkIndex(row, col)) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1");
        }

        if (grid[row][col] != OPEN_SITE) {
            return grid[row][col] == FULL_SITE;
        }

        if (wqu.connected(0, getArrayIndex(row, col))) {
            grid[row][col] = FULL_SITE;
            return true;
        }

        return false;
    }

    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public void open(int row, int col) {
        if (!checkIndex(row, col)) {
            throw new IndexOutOfBoundsException("row and col must be between 0 and N - 1");
        }
        numOfOpenSites++;
        grid[row][col] = row == 0 ? FULL_SITE : OPEN_SITE;
        for (var direction : DIRECTIONS) {
            int newRow = row + direction[ROW_IDX];
            int newCol = col + direction[COL_IDX];
            if (checkIndex(newRow, newCol) &&
                    grid[newRow][newCol] != CLOSE_SITE
            ) {
                wqu.union(getArrayIndex(row, col), getArrayIndex(newRow, newCol));
            }
        }
    }

    public boolean percolates() {
        return wqu.connected(0, finalPos);
    }
}
