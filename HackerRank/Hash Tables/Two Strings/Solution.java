import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {

        //This isn't asking about substrings, it's asking about whether the two strings
        //share letters.

        HashMap<Character, Boolean> hashedWord = new HashMap<Character, Boolean>();

        //hash the first word
        //attempt to retrieve characters from the second
        for (char character : s1.toCharArray()){
            hashedWord.putIfAbsent(character, true);
        }

        //If any exist, then print 'YES'
        for (char character : s2.toCharArray()){
            if (hashedWord.containsKey(character)){
                return "YES";
            }
        }

        //If you get through the word unsuccessfully, print 'NO'
        return "NO";

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
