import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

// Complete the activityNotifications function below.
//Big thanks to github.com/RyanFehr for the idea to use countingsort to solve this problem
    static int activityNotifications(int[] expenditure, int d) {
        LinkedList<Integer> orderOfInsertion = new LinkedList<>();
        int[] relevantDates = new int[201];
        int numberOfInfractions = 0;

        for (int i = 0; i < d; i++){
            relevantDates[expenditure[i]]++;
            orderOfInsertion.addLast(expenditure[i]);
        }

        for (int i = d; i < expenditure.length; i++){
            int dailySpend = expenditure[i];

            //check for fraudulent transaction
            if (findMedian(relevantDates, d) * 2 <= dailySpend){
                numberOfInfractions++;
            }

            //remove element
            int elementToRemove = orderOfInsertion.removeFirst();
            relevantDates[elementToRemove]--;

            //add new element to the list
            orderOfInsertion.addLast(dailySpend);
            relevantDates[dailySpend]++;

        }

        return numberOfInfractions;

    }

    public static double findMedian(int[] relevantDates, int windowSize){
        int counter = 0;
        int index = 0;

        if (windowSize % 2 == 0){//even window size
            counter = windowSize/2;

            while (counter > 0){
                counter -= relevantDates[index];
                index++;
            }
            index--;

            if (counter <= -1){
                return index;
            }

            int secondIndex = index + 1;

            while (relevantDates[secondIndex] == 0){
                secondIndex++;
            }

            return (double)(index + secondIndex)/(2.0);


        } else {//odd window size
            counter = (windowSize / 2) + 1;

            while (counter > 0){
                counter -= relevantDates[index];
                index++;
            }
            return index-1;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
