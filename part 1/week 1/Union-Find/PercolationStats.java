import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;
import java.util.Arrays;

public class PercolationStats {
    private double open_per_trial[];
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new java.lang.IllegalArgumentException();

        this.trials = trials;
        open_per_trial = new double[trials];

        for (int i=0; i<trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            open_per_trial[i] = (double) p.numberOfOpenSites() /(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(open_per_trial);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(open_per_trial);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96*this.stddev()/Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96*this.stddev()/Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(20, 1000);

        StdOut.println("mean                    = " + p.mean());
        StdOut.println("stddev                  = " + p.stddev());
        StdOut.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");

    }

}
