import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.lang.*;

public class Solution {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {

        //can use counting sort
        //would make it run in O(a+b);
	//It is guaranteed to only contain lower case letters
	//saves us from having to either make both strings lowercase at start, or double size of letters arrays
        int[] aLetters = new int[26];
        int[] bLetters = new int[26];

        int lettersToRemove = 0;

        for(char letter : a.toCharArray()){
            aLetters[Character.getNumericValue(letter) - 10]++;
        }

        for(char letter : b.toCharArray()){
            bLetters[Character.getNumericValue(letter) - 10]++;
        }

        for(int i = 0; i < aLetters.length; i++){
            lettersToRemove += Math.abs(aLetters[i] - bLetters[i]);
        }

        return lettersToRemove;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
