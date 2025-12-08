package year2024.day06;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

public class Day06 {
    public static void main(String[] args) {
        String[][] inputArray = fileTo2DArray(getInputPath(Day06.class), "");

        System.out.println("Day 6, Part 1, Number of distinct positions: " + part1(inputArray));
    }

    public static int part1(String[][] inputArray) {
//        Array for visited coordinates
        int[][] visitedArray = new int[inputArray.length][inputArray[0].length];

//        Find start
        int[] coords = new int[]{0,0};
        findStart(inputArray, coords);

//        Direction to move
        int x = -1, y = 0;
        int direction = 0;

//        Move and mark positions loop
        while (rangeCheck(inputArray, coords[0], coords[1])) {
            visitedArray[coords[0]][coords[1]] = 1;

//            Checks next square and changes direction if needed
            if (rangeCheck(inputArray, coords[0] + x, coords[1] + y) &&
                    inputArray[coords[0] + x][coords[1] + y].equals("#")) {
                if (direction == 3) direction = 0;
                else direction++;

                if (direction == 0) {
                    x = -1;y = 0;
                } else if (direction == 1) {
                    x = 0;y = 1;
                } else if (direction == 2) {
                    x = 1;y = 0;
                } else {
                    x = 0;y = -1;
                }
            }

            coords[0] += x;
            coords[1] += y;
        }

        //        Outputs number of visited positions
        return countVisited(visitedArray);
    }

//    Finds the coordinates for starting
    private static void findStart(String[][] inputArray, int [] coords) {
        while(coords[0]++ < inputArray.length) {
            for (int i = 0; i < inputArray[coords[0]].length; i++) {
                if (inputArray[coords[0]][i].equals("^")) {
                    coords[1] = i;
                    return;
                }
            }
        }
    }

//    Calculates total number of positions visited based on array
    private static int countVisited(int[][] visitedArray) {
        int numVisited = 0;

        for (int[] ints : visitedArray) {
            for (int visited : ints) {
                if (visited == 1) numVisited++;
            }
        }
        return numVisited;
    }
}
