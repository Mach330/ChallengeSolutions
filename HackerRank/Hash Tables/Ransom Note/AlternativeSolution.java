import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {

        //hash every word in the magazine, to a value that increments
        HashMap<String, Integer> wordsHash = new HashMap<String, Integer>();

        for (String word : magazine){
            wordsHash.put(word, wordsHash.getOrDefault(word, 0)+1);
        }

        //attempt to hash every word in the note, decrementing the value
        for (String word : note){
        //if attempted to decrement a word that's already at 0, or doesn't exist
        //print no
            if (wordsHash.get(word) == null ||
                wordsHash.get(word) <= 0){
                    System.out.println("No");
                    return;
                } else {
                    wordsHash.replace(word, wordsHash.get(word) - 1);
                }
        }

        //print yes
        System.out.println("Yes");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}
