package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int numOfOpenSites;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF topWqu;
    private int N;
    private static final int CLOSE_SITE = -1;
    private static final int OPEN_SITE = 0;
    private static final int FULL_SITE = 1;
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int topRow;
    private int bottomRow;
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
        // Creating a wqu with 2 extra slots to represent the
        // top of the grid, and the bottom of the grid
        wqu = new WeightedQuickUnionUF(N * N + 2);
        topWqu = new WeightedQuickUnionUF(N * N + 1);

        int finalPos = getArrayIndex(N - 1, N - 1);
        this.topRow = finalPos + 1;
        this.bottomRow = finalPos + 2;
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

        if (topWqu.connected(topRow, getArrayIndex(row, col))) {
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
        if (grid[row][col] != CLOSE_SITE) {
            return;
        }
        numOfOpenSites++;
        grid[row][col] = row == 0 ? FULL_SITE : OPEN_SITE;
        int idx = getArrayIndex(row, col);

        // Connect the site with the surroundings, if the site is at the top row
        // or the bottom row, connect them to the representation
        for (var direction : DIRECTIONS) {
            int newRow = row + direction[ROW_IDX];
            int newCol = col + direction[COL_IDX];
            if (checkIndex(newRow, newCol) && grid[newRow][newCol] != CLOSE_SITE) {
                wqu.union(idx, getArrayIndex(newRow, newCol));
                topWqu.union(idx, getArrayIndex(newRow, newCol));
            }
        }
        if (row == 0) {
            wqu.union(topRow, idx);
            topWqu.union(topRow, idx);
        }

        if (row == N - 1) {
            wqu.union(bottomRow, getArrayIndex(row, col));
        }
    }

    public boolean percolates() {
        return wqu.connected(topRow, bottomRow);
    }

    public static void main(String[] args) {

    }
}
