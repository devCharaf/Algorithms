import edu.princeton.cs.algs4.StdOut;

public class SuccessorWithDelete {
    private int N; // id[i] = parents of node i
    private boolean removed[]; // removed[i] = true is we removed i
    private UnionFindLarge ufl; // successor number (i.e successor of x is the smallest number in )

    public SuccessorWithDelete(int N) {
        this.N = N;
        removed = new boolean[N];
        ufl = new UnionFindLarge(N);
        for (int i = 0; i < N; ++i) {
            removed[i] = false;
        }
    }

    private void remove(int x) {
        removed[x] = false;
        if (x>0 && removed[x-1]) {
            ufl.union(x, x-1);
        }
        if (x<N-1 && removed[x+1]) {
            ufl.union(x, x+1);
        }
    }

    public int successor(int x) {
        if (!removed[x]) {
            return x;
        }
        return ufl.lr[x] + 1;
    }

    public static void main(String[] args) {
        SuccessorWithDelete swd = new SuccessorWithDelete(10);

        swd.remove(4);
        StdOut.println(swd.successor(4));
        swd.remove(5);
        StdOut.println(swd.successor(5));
        swd.remove(8);
        StdOut.println(swd.successor(8));
    }
}
