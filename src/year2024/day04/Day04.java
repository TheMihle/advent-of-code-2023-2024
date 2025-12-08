package year2024.day04;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

public class Day04 {
    public static void main(String[] args) {
        String[][] inputArray = fileTo2DArray(getInputPath(Day04.class));

        System.out.println("Day 4, Part 1, Number of XMAS: " + part1(inputArray));
        System.out.println("Day 4, Part 2, Number of X-MAS: " + part2(inputArray));
    }

    public static int part1(String[][] inputArray) {
        int number = 0;

//        Loops through 2d array and finds X to check for XMAS and counts them
        for (int row = 0; row < inputArray.length; row++) {
            for (int col = 0; col < inputArray[row].length; col++) {
                if (inputArray[row][col].equals("X")) {

//                    Checks for letters in the different directions around the X and returns number
                   number += checkForXmas(inputArray, row, col);
                }
            }
        }

        return number;
    }

//    Checks for letters in the different directions around the input and returns number of matches
    private static int checkForXmas(String[][] array, int row, int col) {
        int number = 0;

        for (int deltaRow = -1; deltaRow <= 1; deltaRow++) {
            for (int deltaCol = -1; deltaCol <= 1; deltaCol++) {
                if (deltaRow == 0 && deltaCol == 0) continue;

//                Skip if coordinate border prevent word.
                if (!rangeCheck(array, row + 3 * deltaRow, col + 3 * deltaCol)) continue;

                if (array[row + 1 * deltaRow][col + 1 * deltaCol].equals("M") &&
                    array[row + 2 * deltaRow][col + 2 * deltaCol].equals("A") &&
                    array[row + 3 * deltaRow][col + 3 * deltaCol].equals("S")) {
                        number++;
                }
            }
        }
        return number;
    }

     public static int part2(String[][] inputArray) {
        int number = 0;

//        Loops through 2D Array and finds A to check for X-MAS and counts them
//        Can ignore spots at the border
        for (int row = 1; row < inputArray.length-1; row++) {
            for (int col = 1; col < inputArray[row].length-1; col++) {
                if (inputArray[row][col].equals("A")) {

//                    Checks for the X-MAS pattern around A and counts number
                    if (checkForXMas(inputArray, row, col)) number++;
                }
            }
        }
        return number;
    }

//    Checks if there is X-MAS pattern
    private static boolean checkForXMas(String[][] array, int row, int col) {
        return ((array[row - 1][col - 1].equals("M") && array[row + 1][col + 1].equals("S") ||
                 array[row - 1][col - 1].equals("S") && array[row + 1][col + 1].equals("M")) &&
                (array[row - 1][col + 1].equals("M") && array[row + 1][col - 1].equals("S") ||
                 array[row - 1][col + 1].equals("S") && array[row + 1][col - 1].equals("M")));
    }

}
