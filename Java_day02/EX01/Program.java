import java.io.*;
import java.util.*;


public class Program {

    private static final LinkedHashSet<String> result = new LinkedHashSet<>();
    private static TreeSet<String> resultSort = new TreeSet<>();

    private static double calculateDenominator (ArrayList<Integer> arrA, ArrayList<Integer> arrB) {
        double denomOne = 0;
        for (int number : arrA){
            denomOne += number * number;
        }
        double denomTwo = 0;
        for (int number : arrB){
            denomTwo += number * number;
        }
        return Math.sqrt(denomOne) * Math.sqrt(denomTwo);
    }
    private static double calculateNumerator(ArrayList<Integer> arrA, ArrayList<Integer> arrB) {
        double numerator = 0;
        for (int i = 0; i < resultSort.size(); i++) {
            numerator += arrA.get(i) * arrB.get(i);
        }
        return numerator;
    }

    private static HashMap<String, Integer> fillArrayWithElements(BufferedReader buffReader) {
        HashMap<String, Integer> map = new HashMap<>();
        String stringBuff;
        try {
            while ((stringBuff = buffReader.readLine()) != null) {
                String[] words = stringBuff.split("\\W");
                for (String word : words) {
                    int count = 1;
                    if (!map.containsKey(word)) {
                        map.put(word, count);
                    } else {
                        map.put(word, map.get(word) + 1);
                    }
                }
                for (Map.Entry<String, Integer> word : map.entrySet()) {
                    result.add(word.getKey());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private static ArrayList<Integer> frequencyOfOccurrence(HashMap<String, Integer> map) {
        ArrayList<Integer> temp = new ArrayList<>();
        for (String word : resultSort) {
            boolean flag = false;
            for (Map.Entry<String, Integer> aitem : map.entrySet()) {
                if (aitem.getKey().equals(word)) {
                    temp.add(aitem.getValue());
                    flag = true;
                }
            }
            if (!flag) {
                temp.add(0);
            }
        }
        return temp;
    }

    private static final String DICTIONARY_FILE_NAME = "dictionary.txt";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: Wrong arguments amount");
            System.exit(-1);
        }

        FileReader fileA = null;
        FileReader fileB = null;
        FileWriter fileDictionary = null;

        try {
            fileA = new FileReader(args[0]);
            fileB = new FileReader(args[1]);
            fileDictionary = new FileWriter(DICTIONARY_FILE_NAME);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Can't find file");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Error: Can't find file " + DICTIONARY_FILE_NAME);
            System.exit(-1);
        }

        HashMap<String, Integer> arrayA = fillArrayWithElements(new BufferedReader(fileA));
        HashMap<String, Integer> arrayB = fillArrayWithElements(new BufferedReader(fileB));

        resultSort = new TreeSet<>(result);

        ArrayList<Integer> countElementA = frequencyOfOccurrence(arrayA);
        ArrayList<Integer> countElementB = frequencyOfOccurrence(arrayB);

        try {
            fileA.close();
            fileB.close();
            for (String word : resultSort) {
                fileDictionary.write(word + " ");
                fileDictionary.flush();
            }
            fileDictionary.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        double numerator = calculateNumerator(countElementA, countElementB);
        numerator = numerator / calculateDenominator(countElementA, countElementB);
        System.out.format("similarity = %.3f", numerator);
    }
}
