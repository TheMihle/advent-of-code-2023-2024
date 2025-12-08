package year2024.day11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static common.ImportFile.fileToSplittedString;
import static common.PathConstructor.getInputPath;

public class Day11 {
    public static void main(String[] args) {
        String[] inputArray = fileToSplittedString(getInputPath(Day11.class), " ");

        System.out.println("Day 11, Part 1, Total number of stones: " + part1(inputArray));
        System.out.println("Day 11, Part 2, Total number of stones: " + part2(inputArray));
    }

    public static int part1(String[] inputArray) {
//        Convert to LinkedList and create a listIterator
        List<String> linkedList = new LinkedList<>(Arrays.asList(inputArray));
        ListIterator<String> listIterator;

//        Calculates number of stones based on rules provided
        for (int i = 0; i < 25; i++) {
            listIterator = linkedList.listIterator();

            while (listIterator.hasNext()) {
                String stringValue = listIterator.next();

                if (stringValue.equals("0")) listIterator.set("1");  // Replace 0 with 1
                else if (stringValue.length() % 2 == 0) {            // If even number, divide into two halves, etc 2000 -> 20, 00 -> 20, 0
                    listIterator.remove();
                    listIterator.add(Long.toString(Long.parseLong(stringValue.substring(0, stringValue.length()/2))));
                    listIterator.add(Long.toString(Long.parseLong(stringValue.substring(stringValue.length()/2))));
                } else {                                             // Multiply by 2024
                    listIterator.set(Long.toString(Long.parseLong(stringValue) * 2024));
                }
            }
        }
        return linkedList.size();
    }

    public static long part2(String[] inputArray) {
//        Convert to HashMap
        Map<String, Long> map = new HashMap<>();
        for (String string : inputArray) map.put(string, 1L);

//        Calculates stones based on rules provided, first time using Stream
//        Considered extracting the if else block to a method but decided not to.
        for (int i = 0; i < 75; i++) {
            Map<String, Long> tempMap = map.entrySet().stream().flatMap(entry -> {
                if (entry.getKey().equals("0")) {                       //  Replace 0 with 1
                    return Stream.of(Map.entry("1", entry.getValue()));
                } else if (entry.getKey().length() % 2 == 0) {          // If even number, divide into two halves, etc 2000 -> 20, 00 -> 20, 0
                    return Stream.of(
                            Map.entry(Long.toString(Long.parseLong(entry.getKey().substring(0, entry.getKey().length()/2))), entry.getValue()),
                            Map.entry(Long.toString(Long.parseLong(entry.getKey().substring(entry.getKey().length()/2))), entry.getValue()));
                } else {                                                // Multiply by 2024
                    return Stream.of(Map.entry(Long.toString(Long.parseLong(entry.getKey()) * 2024), entry.getValue()));
                }
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::sum));
            map = new HashMap<>(tempMap);
        }

//        Calculates the total number of stones
        return map.values().stream().mapToLong(l -> l).sum();
    }
}
