/*
3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to N^2 in the worst
case. You may assume that you can sort the N integers in time proportional to N^2 or better.
*/

import java.util.Arrays;

public class QuadraticThreeSum {

    // Sorting algorithm ~ N^2
    public static int[] sort(int[] S) {
        int N = S.length;
        int Si, Sj;
        for (int i = 0; i < N; i++) {
            Si = S[i];
            for (int j = i + 1; j < N; j++) {
                Sj = S[j];
                if (Si > Sj) {
                    S[i] = Sj;
                    S[j] = Si;
                    Si = S[i];
                }
            }
        }
        return S;
    }

    // 3-Sum algorithm ~ N^2
    public static int count(int[] S) {
        int N = S.length;
        int count = 0;
        int a, b, c, start, end;
        for (int i = 0; i < N; i++) {
            a = S[i];
            start = i+1;
            end = N-1;
            while (start < end) {
                b = S[start];
                c = S[end];
                if (a + b + c == 0) {
                    count++;
                    System.out.println(a + " + " + b + " + " + c + " = 0");
                    start++;
                    end--;
                }
                else if (a + b + c > 0) {
                    end--;
                }
                else {
                    start++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
	    int[] a = {8, -7, 10, -10, 2, -3, 4, -25};
        sort(a);
        System.out.println(Arrays.toString(a));

        int count = count(a);
        System.out.println(count);
    }
}
