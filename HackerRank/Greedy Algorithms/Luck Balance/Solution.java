import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {

        int currentLuck = 0;
        ArrayList<Integer> importantContests = new ArrayList<>();

        //can always lose unimportant contests
        //first pass - increase luck by unimportant contests
        //add important contests to a list 

        for (int[] contest : contests){
            if (contest[1] == 0){
                currentLuck += contest[0];
            } else {
                importantContests.add(contest[0]);
            }
        }

        //sort the list
        Integer[] partialContests = importantContests.toArray(new Integer[0]);
        Arrays.sort(partialContests, Collections.reverseOrder());

        //increase luck by the K biggest contests
        //decrease luck by the remainder
        if (k >= partialContests.length){
            for(int i = 0; i < partialContests.length; i++){
                currentLuck += partialContests[i];
            }
            return currentLuck;
        }

        for (int i = 0; i < k; i++){
            currentLuck += partialContests[i];
        }

        for (int i = k; i < partialContests.length; i++){
            currentLuck -= partialContests[i];
        }


        return currentLuck;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
