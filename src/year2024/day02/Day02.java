package year2024.day02;

import static common.ImportFile.fileToInt2DArray;
import static common.PathConstructor.getInputPath;

public class Day02 {
    public static void main(String[] args) {
        int[][] intArray = fileToInt2DArray(getInputPath(Day02.class), " ");

        System.out.println("Day 2, Part 1, Number of safe reports: " + part1(intArray) );
    }

    public static int part1(int[][] intArray) {
        //        Calculates number of safe reports
        int numberSafe = 0;

        for (int[] ints : intArray) {
            int steps = 0;
            for (int i = 0; i < ints.length-1; i++) {
                int difference = ints[i+1] - ints[i];
                if (difference > 0 && difference < 4) {
                    steps++;
                } else if (difference < 0 && difference > -4) {
                    steps--;
                }
            }

            int diffFromLength = Math.abs(steps) - (ints.length-1);
            if (diffFromLength == 0) numberSafe++;
        }
        return numberSafe;
    }
}
