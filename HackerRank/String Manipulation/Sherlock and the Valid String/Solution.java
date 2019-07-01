import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {

        //similar to previous problems, here I can use counting sort
        int[] letterAmounts = new int[26];
        for (char character : s.toCharArray()){
            letterAmounts[Character.getNumericValue(character) - 10]++;
        }

        int max = 0;
        int min = 0;
        int maxCount = 0;
        int minCount = 0;
        int totalCount = 0;

        for (int i = 0; i < letterAmounts.length; i++){
            //find the max and min values, and the amount of times they occur
            if (letterAmounts[i] == 0){
                continue;
            }

            totalCount++;

            if (max == 0 || min == 0){
                max = letterAmounts[i];
                min = letterAmounts[i];
                maxCount++;
                minCount++;
                continue;
            }

            if (letterAmounts[i] == max){
                maxCount += 1;
            }

            if (letterAmounts[i] == min){
                minCount += 1;
            }

            if (letterAmounts[i] > max){
                max = letterAmounts[i];
                maxCount = 1;
            }

            if (letterAmounts[i] < min){
                min = letterAmounts[i];
                minCount = 1;
            }
        }
        //check for validity and return outputs
        if (max == min || (min + 1 == max && maxCount == 1) || (min == 1 && minCount == 1 && maxCount == totalCount - 1)){
            return "YES";
        }

        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
