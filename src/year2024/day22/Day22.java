package year2024.day22;

import java.util.ArrayList;
import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Day22 {
    public static void main(String[] args) {
        List<String> initialSecretNumbers = fileToArray(getInputPath(Day22.class));

//        Convert secret numbers
        List<Long> secretNumbers  = new ArrayList<>();
        for (String initialSecretNumber : initialSecretNumbers) {
            secretNumbers.add(Long.parseLong(initialSecretNumber));
        }

        System.out.println("Day 22, Part 1, Sum of 2000th secret numbers: " + part1(secretNumbers));
    }

    public static long part1(List<Long> secretNumbers) {
//        Calculate 2000th secret number
        for (int i = 0; i < 2000; i++) {
            secretNumbers.replaceAll(Day22::calcSecretNumber);
        }

//        Calculate sum of secret numbers
        long sumSecretNumber = 0;
        for (Long secretNumber : secretNumbers) {
            sumSecretNumber += secretNumber;
        }
        return sumSecretNumber;
    }

    private static long mix(long value, long secretNumber){
        return value ^ secretNumber;
    }

    private static long prune(long secretNumber){
        return secretNumber % 16777216;
    }

    private static long calcSecretNumber(long secretNumber) {
        secretNumber = prune(mix(secretNumber * 64, secretNumber));
        secretNumber = prune(mix(secretNumber / 32, secretNumber));
        secretNumber = prune(mix(secretNumber * 2048, secretNumber));
        return secretNumber;
    }
}
