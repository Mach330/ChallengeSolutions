import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

//This contains two solutions that work almost identically. 
//Initially the first was created, but HackerRank was unable to compile and run it. The assumption was made that too much memory was in use, and a more memory efficient method was written.
//It ended up being a weird thing with HackerRank, as it now accepts and gives full points to both implementations
//I am especially proud of this as it is the first HackerRank hard problem to which the solution is entirely my own
    static long countInversions(int[] arr) {
        //time to implement a mergesort my dudes

        long resut = mergeSortCountInversions(arr, 0, arr.length);
        //long result = mergeSortCountInversions(arr);
        return result;
    }

    static long mergeSortCountInversions(int[] arr){
        if (arr.length <= 1){
            return 0;
        }

        long numberOfInversions = 0;

        int[] leftArray = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] rightArray = Arrays.copyOfRange(arr, arr.length/2, arr.length);

        numberOfInversions += mergeSortCountInversions(leftArray);
        numberOfInversions += mergeSortCountInversions(rightArray);

        int leftCounter = 0;
        int rightCounter = 0;
        int i = 0;

        while (leftCounter < leftArray.length && rightCounter < rightArray.length){
            if (leftArray[leftCounter] > rightArray[rightCounter]){
                arr[i] = rightArray[rightCounter];
                numberOfInversions += (leftArray.length - leftCounter);
                rightCounter++;
            } else {
                arr[i] = leftArray[leftCounter];
                leftCounter++;
            }

            i++;
        }

        //if entire left array has been added, make the rest the right array
        if (leftCounter == leftArray.length){
            for(int j = i; j<arr.length; j++){
                arr[j] = rightArray[rightCounter];
                rightCounter++;
            }
        } else { //otherwise make the rest the left array
            for(int j = i; j<arr.length; j++){
                arr[j] = leftArray[leftCounter];
                leftCounter++;
            }
        }

        //at this point the array should be sorted
        return numberOfInversions;
    }

    //The endingIndex is exclusive
    static long mergeSortCountInversions(int[] arr, int startingIndex, int endingIndex){
        //return clause
        if (endingIndex - startingIndex <= 1){
            return 0;
        }

        long numberOfInversions = 0;

        //split the array in two, and work each of those individually
        int leftEndingIndex = startingIndex + (endingIndex - startingIndex)/2;

        numberOfInversions += mergeSortCountInversions(arr, startingIndex, leftEndingIndex);
        numberOfInversions += mergeSortCountInversions(arr, leftEndingIndex, endingIndex);

        //startingIndex -> leftEndingIndex is now sorted
        //leftEndingIndex -> endingIndex is now sorted

        //create a temp array that holds what it currently looks like, one for the left and right
        int[] tempLeftArray = Arrays.copyOfRange(arr, startingIndex, leftEndingIndex);
        int[] tempRightArray = Arrays.copyOfRange(arr, leftEndingIndex, endingIndex);

        int leftCounter = 0;
        int rightCounter = 0;
        int arrCounter = startingIndex;

        while (leftCounter < tempLeftArray.length && rightCounter < tempRightArray.length) {
            if (tempLeftArray[leftCounter] > tempRightArray[rightCounter]){
                arr[arrCounter] = tempRightArray[rightCounter];
                numberOfInversions += tempLeftArray.length - leftCounter;
                rightCounter++;
            } else {
                arr[arrCounter]  = tempLeftArray[leftCounter];
                leftCounter++;
            }
            arrCounter++;
        }

        //add the remainder of the remaining array
        if (leftCounter == tempLeftArray.length){
            while (rightCounter < tempRightArray.length){
                arr[arrCounter] = tempRightArray[rightCounter];
                rightCounter++;
                arrCounter++;
            }
        } else {
            while(leftCounter < tempLeftArray.length){
                arr[arrCounter] = tempLeftArray[leftCounter];
                leftCounter++;
                arrCounter++;
            }
        }

        //should be sorted at this point
        return numberOfInversions;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
