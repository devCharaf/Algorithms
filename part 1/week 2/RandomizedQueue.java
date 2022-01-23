/*
Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly
at random among items in the data structure.
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (n == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (n == s.length) resize(2 * s.length);
        s[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randIdx = StdRandom.uniform(0, n);

        Item item = s[randIdx];
        s[randIdx] = s[n - 1];
        s[n - 1] = null;
        n--;

        if (n > 0 && n == s.length / 4) resize(s.length / 2);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int randIdx = 0;

        return s[randIdx];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = s[i];
        s = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomArrayIterator();
    }

    private class RandomArrayIterator implements Iterator<Item> {
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return s[--i];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        /* Nothing here */
    }

}
