import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class UnionFindLarge {
    private int id[]; // id[i] = parents of node i
    private int sz[]; // sz[i] = size of node i
    int lr[]; // larger number in the connected components

    public UnionFindLarge(int N) {
        id = new int[N];
        sz = new int[N];
        lr = new int[N];
        for (int i = 0; i < N; ++i) {
            id[i] = i;
            sz[i] = 1;
            lr[i] = i;
        }
    }

    private int root(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]]; // path compression
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootp = root(p);
        int rootq = root(q);
        if (rootp == rootq) return;
        if (sz[rootp] < sz[rootq]) {
            id[rootp] = rootq;
            sz[rootq] += sz[rootp];
        } else {
            id[rootq] = rootp;
            sz[rootp] += sz[rootq];
        }
        if (p > q) {
            lr[q] = p;
        } else {
            lr[p] = q;
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        UnionFindLarge ufl = new UnionFindLarge(10);

        ufl.union(1, 9);
        StdOut.println(Arrays.toString(ufl.lr));
        ufl.union(9, 8);
        StdOut.println(Arrays.toString(ufl.lr));
    }
}
