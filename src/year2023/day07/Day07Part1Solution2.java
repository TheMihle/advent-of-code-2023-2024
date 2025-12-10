// Second solution/attempt, no RegEx, using loops and hashmap instead.

package year2023.day07;

import java.util.*;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;
import static year2023.day07.Day07Part1Solution1.*;

public class Day07Part1Solution2 {
    public static void main(String[] arg){
        List<String> inputLines = fileToArray(getInputPath(Day07Part1Solution2.class));

//        Convert array to an 2D array
        List<ArrayList<String>> cardArray = arrayTo2DArray(inputLines);

//        Sorted map for hand value and bids
        Map<Long, Integer> handValueBidMap = new TreeMap<>();

        for (List<String> stringArray : cardArray) {

//            Hashmap for possible characters
            Map<Character, Integer> cardMap = generateCardMap();

//            Counts characters and put it in to hashmap
            for (char c : stringArray.getFirst().toCharArray()){
                cardMap.put(c, cardMap.get(c)+1);
            }

//            Finds hand value with combination bonus
            long key = handValue(stringArray.get(0));

//            Five of a kind
            if (cardMap.containsValue(5)){
                key += 60000000000L;

//                Four of a kind
            } else if (cardMap.containsValue(4)) {
                key += 50000000000L;

//                Full house
            } else if (cardMap.containsValue(3) && cardMap.containsValue(2)) {
                key += 40000000000L;

//                Tree of a kind
            } else if (cardMap.containsValue(3)) {
                key += 30000000000L;
//
//                One/two pairs
            } else if (cardMap.containsValue(2)) {
                for (Map.Entry<Character, Integer> entry : cardMap.entrySet()){
                    if (entry.getValue() == 2){
                        key += 10000000000L;
                    }
                }
            }

//            Add bid amount to sorted map with a hands value as key
            handValueBidMap.put(key, Integer.parseInt(stringArray.get(1)));
        }

//        Calculates sum based on bid amount and rank
        long totalWinnings = calculateWinnings(handValueBidMap);

        System.out.println("Day 7, Part 1, Total winnings: " + totalWinnings);
    }

    private static Map<Character, Integer> generateCardMap() {
        char[] cards = {'2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A'};

        Map<Character, Integer> cardMap = new HashMap<>();
        for (char card : cards) {
            cardMap.put(card, 0);
        }
        return cardMap;
    }
}
