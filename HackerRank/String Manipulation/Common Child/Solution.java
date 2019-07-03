import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

//Big thanks to https://en.wikipedia.org/wiki/Longest_common_subsequence_problem, suggested in a discussion section, which explained the theory behind this problem
    static int commonChild(String s1, String s2){
        int[][] arr = new int[s1.length() + 1][s2.length() + 1];

        //At a glance, it looks like this could be better done using recursion. 
	//without memoization, however, recursion is much slower
	//for further explanation, see the Wikipedia link provided above
        for(int i = 0; i < s1.length(); i++){
            for (int j = 0; j < s2.length(); j++){
		//if the substrings end in the same character, increase the prev value by 1
                if (s1.charAt(i) == s2.charAt(j)){
                    arr[i+1][j+1] = arr[i][j] + 1;
                } else {
			//else grab the biggest value of reducing the substrings by 1
                    arr[i+1][j+1] = Math.max(arr[i+1][j], arr[i][j+1]);
                }
            }
        }

        return arr[s1.length()][s2.length()];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
