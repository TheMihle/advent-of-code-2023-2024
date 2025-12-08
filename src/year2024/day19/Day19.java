package year2024.day19;

import java.util.*;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Day19 {
    public static void main(String[] args) {
        List<String> designs = fileToArray(getInputPath(Day19.class));

//        Extract patterns
        String[] patterns = designs.getFirst().split(", ");
        designs.removeFirst();
        designs.removeFirst();

        System.out.println("Day 19, Part 1, Number of possible designs: " + part1(designs, patterns));
        System.out.println("Day 19, Part 2, Number of possible design combinations: " + part2(designs, patterns));
    }

    public static int part1(List<String> designs, String[] patterns) {
//        Creates regex pattern for the patterns
        String pattern = "(" + String.join("|", patterns) + ")*";

//        Counts possible designs with provided patterns
        int possibleDesings = 0;
        for (String design : designs) {
            if (design.matches(pattern)) possibleDesings++;
        }
        return possibleDesings;
    }

    public static long part2(List<String> designs, String[] patterns) {
//       Add patterns to HashSet
        Set<String> hashPatterns = new HashSet<>(Arrays.asList(patterns));

        int longestPattern = 0;
        for (String pattern : patterns) {
            if (pattern.length() > longestPattern) longestPattern = pattern.length();
        }

//        Counts possible combinations with provided patterns with memory
        long possibleCombinations = 0;
        Map<String, Long> memory = new HashMap<>();

        for (String design : designs) {
            possibleCombinations += findMatch(design, hashPatterns, memory, longestPattern);
        }

        return possibleCombinations;
    }

//    Recursive method that finds matches at the start of the design, calls itself recursively on the next part of the design.
//    Returns number of possible combinations.
    private static long findMatch(String subDesign, Set<String> patterns, Map<String, Long> memory, int maxLength) {
        if (subDesign.isEmpty()) return 1;
//        Skips if substring have already been calculated before, happens A LOT of times
        if (memory.containsKey(subDesign)) return memory.get(subDesign);

        long combinations = 0;
        for (int i = 1; i <= maxLength; i++) {
            if (i > subDesign.length()) continue;
            if (patterns.contains(subDesign.substring(0, i))) {
                combinations += findMatch(subDesign.substring(i), patterns, memory, maxLength);
            }
        }

//        Save new calculations of number combinations
        memory.put(subDesign, combinations);
        return combinations;
    }
}
