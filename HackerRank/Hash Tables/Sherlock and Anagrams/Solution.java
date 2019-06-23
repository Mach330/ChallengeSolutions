import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

//The original function. runs in O(n^3), but works. 
    static int sherlockAndAnagrams(String s) {

        int counter = 0;

        //find matching pairs using brute force
        for (int n = 1; n < s.length(); n++) {
            for (int i = 0; i < s.length() - n; i++) {
                for (int j = i + 1; j < s.length() - (n-1); j++) {
                    char[] sub1 = s.substring(i, i+n).toCharArray();
                    char[] sub2 = s.substring(j, j+n).toCharArray();
                    Arrays.sort(sub1);
                    Arrays.sort(sub2);
                    if (Arrays.equals(sub1, sub2)){
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

//An improved solution, which runs in O(n^2)
     static int betterSherlockAndAnagrams(String s){

        int counter = 0;
        HashMap<String, Integer> foundSubstrings;
        String subString;

        //sliding window size
        for (int i = 1; i < s.length(); i++){
            foundSubstrings = new HashMap<>();

            for (int j = 0; j+i <= s.length(); j++){
                char[] charArray = s.substring(j, j+i).toCharArray();
                Arrays.sort(charArray);
                subString = new String(charArray);

                if (foundSubstrings.containsKey(subString)){
                    counter += foundSubstrings.get(subString);
                    foundSubstrings.replace(subString, foundSubstrings.get(subString) + 1);
                } else {
                    foundSubstrings.put(subString, 1);
                }
            }

        }
        return counter;
    }

//Given code, left here for completion purposes
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
