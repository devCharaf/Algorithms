/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import java.util.Scanner;

public class HelloGoodbye {
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name1 = scan.next();
        String name2 = scan.next();
        // String name1 = "Charaf";
        // String name2 = "Achraf";
        System.out.println("Hello " + name1 + " and " + name2 + ".");
        System.out.println("Goodbye " + name2 + " and " + name1 + ".");
    }
}
