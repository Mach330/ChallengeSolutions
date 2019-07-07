import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int k, int[] c) {

        //the flowers will always be purchased in most expensive to least expensive order
        //the first group of amount k will be x1
        //second group will be x2
        //etc until all the flowers are bought

        //sort the array in descending order
        int n = c.length;
        int minCost = 0;
        int tempCount = 0;
        int previousPurchases = 0;

        Arrays.sort(c);

        //if we have more friends than there are flowers, just buy the cheapest ones
        if(k >= n){
            for(int i = 0; i < n; i++)
                minCost += c[i];
        } else {
            //for each 'block' of flowers of size friends, buy the most expensive ones while increasing the minimum cost by 1
            for(int i = n - 1; i >= 0; i--){
                if(tempCount == k){
                    tempCount = 0;
                    previousPurchases++;
                }
                minCost += (previousPurchases + 1) * c[i];
                tempCount++;
            }
        }

        return minCost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = getMinimumCost(k, c);

        bufferedWriter.write(String.valueOf(minimumCost));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
