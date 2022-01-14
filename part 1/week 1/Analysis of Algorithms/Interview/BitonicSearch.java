/*
Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed
immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of nn distinct integer
values, determines whether a given integer is in the array.

- Standard version: Use ~ 3log(N) compares in the worst case.
- Signing bonus: Use ~ 2log(N) compares in the worst case (and prove that no algorithm can guarantee to perform fewer
than ~ 2log(N) compares in the worst case).
*/

import java.util.Arrays;

public class BitonicSearch {

    // Finds index of maximum element in a bitonic array of size N. Complexity ~ log(N)
    public static int findMax(int[] S, int lo, int hi) {
        int N = S.length;

        if (lo == hi) {
            return lo;
        }

        if (hi == lo + 1 && S[lo] >= S[hi]) {
            return lo;
        }

        if (hi == lo + 1 && S[lo]<= S[hi]) {
            return hi;
        }

        int m = lo + (hi - lo) / 2;

        if (S[m] > S[m - 1] && S[m] > S[m + 1]) {
            return m;
        }
        else if (S[m] > S[m - 1] && S[m] < S[m + 1]) {
            lo = m + 1;
            return findMax(S, lo, hi);
        }
        else {
            hi = m - 1;
            return findMax(S, lo, hi);
        }
    }

    // Binary search in an ascending sorted array
    public static int ascendingBinarySearch(int[] S, int lo, int hi, int key) {
        while (lo <= hi) {
            int m = lo + (hi -lo) / 2;
            if (S[m] == key) return m;
            else if (S[m] > key) {
                hi = m - 1;
            }
            else {
                lo = m + 1;
            }
        }
        return -1;
    }

    // Binary search in an descending sorted array
    public static int descendingBinarySearch(int[] S, int lo, int hi, int key) {
        while (lo <= hi) {
            int m = lo + (hi -lo) / 2;
            if (S[m] == key) return m;
            else if (S[m] > key) {
                lo = m + 1;
            }
            else {
                hi = m - 1;
            }
        }
        return -1;
    }

    // search index of key in a Bitonic array of size N. Complexity ~ log(N)
    public static int searchBitonic(int[] S, int key) {
        int N = S.length;
        int m = findMax(S, 0, N-1);

        if (key > S[m]) return -1;
        else if (S[m] == key) {
            return m;
        }
        else {
            int temp = ascendingBinarySearch(S, 0, m-1, key);
            if (temp != -1) return temp;
            return descendingBinarySearch(S, m+1, N-1, key);
        }
    }

    public static void main(String[] args) {
        int[] a = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};

        System.out.println(Arrays.toString(a));

        System.out.println(searchBitonic(a, 500));
        System.out.println(searchBitonic(a, 20));
        System.out.println(searchBitonic(a, 2));
    }
}
