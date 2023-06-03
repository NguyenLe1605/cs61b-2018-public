package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double mean, stddev;
    private double[] results;
    private final double CONFIDENCE_CONSTANT = 1.96;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be <= 0");
        }
        this.T = T;
        results = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                while (true) {
                    int row = StdRandom.uniform(N);
                    int col = StdRandom.uniform(N);
                    if (!perc.isOpen(row, col)) {
                        perc.open(row, col);
                        break;
                    }
                }
            }
            results[i] = perc.numberOfOpenSites() * 1.0 / (N * N);
        }

        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        return this.stddev;
    }

    public double confidenceLow() {
        return this.mean - (CONFIDENCE_CONSTANT * stddev) / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return this.mean + (CONFIDENCE_CONSTANT * stddev) / Math.sqrt(T);
    }
}
