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

static List<Integer> freqQuery(List<List<Integer>> queries) {

        //use a hashmap to keep track of what is in there
        Map<Integer, Integer> dataStructure = new HashMap<>();
        Map<Integer, Integer> frequencyStructure = new HashMap<>();

        //use a list<Integer> for type 3 queries to return
        List<Integer> toReturn = new ArrayList<>();
        int currentFrequency;
        int newFrequency;
        int queryType;
        int queryValue;

        //parse each query individually
        for (List<Integer> query : queries){
            queryType = query.get(0);
            queryValue = query.get(1);

            if (queryType == 1) {
                //add to the hashmap
                //reduce the current frequency in the frequency map by 1
                //cannot drop below 0
                if ((currentFrequency = dataStructure.getOrDefault(queryValue, 0)) > 0) {
                    frequencyStructure.put(currentFrequency, frequencyStructure.get(currentFrequency)-1);
                }

                //increase the frequency in the data structure by 1
                dataStructure.put(queryValue, dataStructure.getOrDefault(queryValue, 0)+1);

                //increase the new frequency in the frequency map by 1
                newFrequency = dataStructure.get(queryValue);
                frequencyStructure.put(newFrequency, frequencyStructure.getOrDefault(newFrequency, 0) + 1);

            } else if (queryType == 2) {
                //remove an instance from the hashmap

                if ((currentFrequency = dataStructure.getOrDefault(queryValue, 0)) > 0) {
                    //reduce the current frequency  by 1
                    frequencyStructure.put(currentFrequency, frequencyStructure.get(currentFrequency) - 1);

                    dataStructure.put(queryValue, dataStructure.get(queryValue) - 1);

                    //increase the new frequency by 1
                    newFrequency = dataStructure.get(queryValue);
                    frequencyStructure.put(newFrequency, frequencyStructure.getOrDefault(newFrequency, 0) + 1);
                }

            } else {
                //see if there exists a number with the given frequency
                toReturn.add(frequencyStructure.getOrDefault(queryValue, 0) > 0? 1 : 0);
            }
        }
        return toReturn;
    }

//given code, left here for completion purposes
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
