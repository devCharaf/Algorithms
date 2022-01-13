/*
Given a social network containing nn members and a log file containing mm timestamps at which times pairs of members formed friendships, 
design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend).
Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. 

The running time of your algorithm should be m*log(n) or better and use extra space proportional to n.
*/

import edu.princeton.cs.algs4.StdOut;

import java.util.concurrent.ThreadLocalRandom;

public class SocialNetworkConnectivity {
    private int id[]; // id[i] = parents of node i
    private int sz[]; // sz[i] = size of node i
    private int count; // number of connected components

    public SocialNetworkConnectivity(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; ++i) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
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
        count--;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public static void main(String[] args) {
        SocialNetworkConnectivity snc = new SocialNetworkConnectivity(10);
        int l = 0;

        while (snc.count != 1) {
            // generate two random numbers between 0 and 10
            int rand1 = ThreadLocalRandom.current().nextInt(0, 10);
            int rand2 = ThreadLocalRandom.current().nextInt(0, 10);

            // make friendship between rand1 and rand2
            if (rand1 != rand2) {
                snc.union(rand1, rand2);
                l++;
            }
        }

        StdOut.println(l);
    }
}
