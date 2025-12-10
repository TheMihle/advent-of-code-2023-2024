package year2023.day09;

import java.util.Arrays;
import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Day09 {
    public static void main(String[] args) {
        List<String> inputLines = fileToArray(getInputPath(Day09.class));

        long extrapolatedValuesSumPart1 = part1(inputLines);
        long extrapolatedValuesSumPart2 = part2(inputLines);

        System.out.println("Day 9, Part 1, Sum of extrapolated values: " + extrapolatedValuesSumPart1);
        System.out.println("Day 9, Part 2, Sum of extrapolated values: " + extrapolatedValuesSumPart2);
    }

    private static long part1(List<String> inputLines) {
        System.out.println("---------------------\nPART 1:\n---------------------");
        long extrapolatedValuesSum = 0;

        for (String inputLine : inputLines) {
//            Splits string to array and converts to int array
            int[] sequence = splitToInts(inputLine);

//            Calculates next step via recursive method
            int nextValue = sequence[sequence.length-1] + rowCalcNext(sequence);
            System.out.println("\n");

            extrapolatedValuesSum += nextValue;
        }
        return extrapolatedValuesSum;
    }

    public static long part2(List<String> inputLines) {
        System.out.println("---------------------\nPART 2:\n---------------------");
        long extrapolatedValuesSum = 0;

        for (String inputLine : inputLines) {
//            Splits string to array and converts to int array
            int[] sequence = Day09.splitToInts(inputLine);

//            Calculates next step via recursive method
            int previousValue = sequence[0] - rowCalcPrevious(sequence);
            System.out.println("\n");

            extrapolatedValuesSum += previousValue;
        }
        return extrapolatedValuesSum;
    }

//       Splits string to array and converts to int array
    static int[] splitToInts(String inputLine) {
        String[] sSequence = inputLine.split(" ");

        int[] sequence = new int[sSequence.length];
        for (int i = 0; i < sSequence.length; i++) {
            sequence[i] = Integer.parseInt(sSequence[i]);
        }

        System.out.println(Arrays.toString(sequence));
        return sequence;
    }


//    Calculating next row, returns calculated next step
    static int rowCalcNext(int[] inputArray){
        int[] calcArray = buildDifferenceRow(inputArray);

        boolean allZero = Arrays.stream(calcArray).allMatch((i) -> i == 0);
        if (allZero){
            return 0;
        } else {
            return calcArray[calcArray.length-1] + rowCalcNext(calcArray);
        }
    }

//    Calculating next row, returns calculated next step
    static int rowCalcPrevious(int[] inputArray){
        int[] calcArray = buildDifferenceRow(inputArray);

        boolean allZero = Arrays.stream(calcArray).allMatch((i) -> i == 0);
        if (allZero){
            return 0;
        } else {
            return calcArray[0] - rowCalcPrevious(calcArray);
        }
    }

    private static int[] buildDifferenceRow(int[] inputArray) {
        int[] calcArray = new int[inputArray.length-1];
        for (int i = 1; i < inputArray.length; i++) {
            calcArray[i-1] = inputArray[i] - inputArray[i-1];
        }
        System.out.println(Arrays.toString(calcArray));
        return calcArray;
    }
}
