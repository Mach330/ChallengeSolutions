import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {

	//Instead of adding to each element, we only add to the index of the first
	//and remove from the index of the last
	//This improves complexity from O(nm) to O(n+m)
        long[] array = new long[n + 2];
        long currentValue = 0;
        long maximum = 0;

        for (int i = 0; i < queries.length; i++){
            array[queries[i][0]] += queries[i][2];
            array[queries[i][1]+1] -= queries[i][2];
        }
	
	//While traversing the array, add the next value
	//compare the new value after the addition
        for (int i = 0; i < array.length; i++){
            currentValue += array[i];
            if (currentValue > maximum){
                maximum = currentValue;
            }
        }

        return maximum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
