import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        //we have to make 2 passes of the string

        long substrcount = 0;

        ArrayList<Character> values = new ArrayList<>();
        ArrayList<Integer> lengths = new ArrayList<>();

	//initialising to the first value to make the logic a little easier
        char[] arr = s.toCharArray();
        int amount = 1;
        char currentchar = arr[0];

        //the first pass we group all continuous values and how long they are
        //increase the count by (n * (n+1))/2 to get all the substrings within a continuous line
	//same as (x + (x-1) + (x-2)... + 1)
        for (int i = 1; i < n; i++){
            if (arr[i] == currentchar){
                amount++;
            } else {
                values.add(currentchar);
                lengths.add(amount);
                substrcount += (amount*(amount+1))/2;
                amount = 1;
            }
            currentchar = arr[i];
        }

	//don't forget to include the values that are still in the buffer
        values.add(currentchar);
        lengths.add(amount);
        substrcount += (amount*(amount+1))/2;

        //the second pass we look for any values where the length is one, and they have the same letter on each side
        for (int i = 1; i < (lengths.size() - 1); i++){
            if (lengths.get(i) == 1){
                if (values.get(i-1) == values.get(i+1)){
                    //add the min of the two letters
                    substrcount += Math.min(lengths.get(i-1), lengths.get(i+1));
                }
            }
        }

        return substrcount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
