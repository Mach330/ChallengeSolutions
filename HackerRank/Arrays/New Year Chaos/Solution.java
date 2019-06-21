import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {

	//BigO(n)
        int numberOfBribes = 0;
        LinkedList<Integer> positions = new LinkedList<Integer>();
        //create the default list
        for(int i = 1; i <= q.length; i++){
            positions.add(i);
        }


	/*The algorithm is as follows
	//The original position of each person is given by the positions algorithm.
	//We compare the original position with the actual position.
	//If the actual position is more than 2 forward, it is in an error state, as given by the requirements
	//Otherwise, we add the difference between positions to a counter, and remove the value from the original list
	//Removing the value from the original list removes that value's effect on said list. 
	//Other solutions instead swap the values back to their original positions. 
	//I think this is BigO(n*n), as removal from a linked list is BigO(n)
	*/
        for (int i = 0; i < q.length; i++){
            if (positions.indexOf(q[i]) > 2){
                System.out.println("Too chaotic");
                return;
            }
            numberOfBribes += positions.indexOf(q[i]);
            positions.remove((Integer)q[i]);
        }
        System.out.println(numberOfBribes);
    }

//Everything below here is given code, that I have kept in here for completion's sake.
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
