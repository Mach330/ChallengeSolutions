import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {

        //Go through the array. If the element isn't in the right place
        //put it in the right place
        //Check until the right element is in that place
        //move to the next element
        //keep a counter and repeat until you reach the end of the array

        int counter = 0;

        for (int i = 0; i < arr.length; i++){
            while (arr[i] != i+1){
                int temp = arr[i];
                arr[i] = arr[temp-1];
                arr[temp-1] = temp;
                counter++;
            }
        }
        return counter;
    }

//Given code to allow for Hackerrank's test cases.
//provided here for completeness
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
