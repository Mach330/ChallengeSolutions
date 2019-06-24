import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the countTriplets function below.
   static long countTriplets(List<Long> arr, long r) {

        //make a hashmap of all the values in arr and their indices
        long counter = 0;

        HashMap<Long, List<Integer>> valueIndices = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            Long currentValue = arr.get(i);
            if (!valueIndices.containsKey(currentValue)) {
                valueIndices.put(currentValue, new ArrayList<>());
            }

            valueIndices.get(currentValue).add(i);
        }

	//we are able to retrieve a list of all indices of a specific number in constant time
        for (int i = 1; i < arr.size()-1; i++){
            Long currentValue = arr.get(i);

		//confirming that the current value is viable to have a lower value
            if (currentValue%r != 0) {
                continue;
            }

		//for easy access later
            long lowerValue = currentValue/r;
            long upperValue = currentValue*r;

            long lowerCount = 0;
            long upperCount = 0;
	
		//There must be a lower and an upper value. check this in constant time now.
		//avoids null pointer exceptions later down the line.
            if (!(valueIndices.containsKey(lowerValue) && valueIndices.containsKey(upperValue))){
                continue;
            }

		//we know that the list of indices in the hashmap is sorted, as we put everything in there in in traversing order
		//binary search therefore works in O(log n) as opposed to O(n) of stepping through to find indices lower/higher than current position
            lowerCount = Collections.binarySearch(valueIndices.get(lowerValue), i);
            if (lowerCount < 0) {
                lowerCount = -1 - lowerCount;
            }

            upperCount = Collections.binarySearch(valueIndices.get(upperValue), i);
            if (upperCount < 0) {
                upperCount = -1 - upperCount - 1;
            }

		//semantics to parse to the information we need
            upperCount = valueIndices.get(upperValue).size() - 1 - upperCount;
	
		//each lower + upper pair is a valid combination for a triple
            counter += lowerCount * upperCount;
        }

        return counter;
    }

	//given code, left here for completion purposes
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
