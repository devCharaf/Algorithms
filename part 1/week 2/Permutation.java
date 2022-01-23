/*
Client. Write a client program Permutation.java that takes an integer k as a command-line argument; reads a sequence of
strings from standard input using StdIn.readString(); and prints exactly k of them, uniformly at random.
Print each item from the sequence at most once.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    private final RandomizedQueue<String> rq;

    // construct an empty randomized queue
    public Permutation() {
        rq = new RandomizedQueue<String>();
    }

    private void makeRq() {
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
    }

    private void randomK(int k) {
        String item;
        for (int myK = k; myK > 0; myK--) {
            item = rq.dequeue();
            StdOut.println(item);
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        Permutation rq = new Permutation();

        rq.makeRq();

        rq.randomK(Integer.parseInt(args[0]));

    }

}

