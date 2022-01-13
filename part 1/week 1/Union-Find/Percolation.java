import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean grid[][];
    private WeightedQuickUnionUF uf;
    private int open_sites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new java.lang.IllegalArgumentException();

        this.n = n;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n*n + 2);
        open_sites = 0;

        for (int i = 0; i < n; ++i) {
            // connect upper and lower row to one node
            uf.union(i+1, 0);
            uf.union(n*n - i, n*n + 1);

            for (int j = 0; j < n; ++j) {
                grid[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new java.lang.IllegalArgumentException();

        if (!grid[row-1][col-1]) {
            open_sites++;
            grid[row-1][col-1] = true;
        }

        // connecting neighbouring open sites
        if (row < n && isOpen(row + 1, col)) {
            uf.union((row-1)*n + col, row*n + col);
        }
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union((row-1)*n + col, (row-2)*n + col);
        }
        if (col < n && isOpen(row, col + 1)) {
            uf.union((row-1)*n + col, (row-1)*n + col + 1);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union((row-1)*n + col, (row-1)*n + col - 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new java.lang.IllegalArgumentException();

        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) throw new java.lang.IllegalArgumentException();

        return uf.connected(0, (row-1)*n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return open_sites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, n*n + 1);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        StdOut.println(p.percolates());

        p.open(1, 2);
        p.open(2, 2);
        p.open(3, 1);

        StdOut.println(p.percolates());

        p.open(2, 1);

        StdOut.println(p.percolates());

        StdOut.println(p.numberOfOpenSites());
    }
}
