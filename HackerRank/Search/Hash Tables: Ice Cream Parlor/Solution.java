import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {

        //one-pass hash table solution
        //keep a current minimum
        //hash the element
        //check already hashed elements for an answer
        //if the answer is already in there, return it

        HashMap<Integer, Integer> values = new HashMap<>();
        int i = 1;

        for (int value : cost){
            //check the hashmap for a new minimum
            if (values.containsKey(money - value)){
                if (i > values.get(money - value)){
                    System.out.println(values.get(money - value) + " " + i);
                } else {
                    System.out.println(i + " " + values.get(money - value));
                }
                return;
            }

            //add the new value to the hashmap
            values.put(value, i);
            i++;
        }

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }
}
