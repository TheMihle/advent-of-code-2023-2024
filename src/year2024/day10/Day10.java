package year2024.day10;

import common.Direction;

import java.util.HashSet;
import java.util.Set;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileToInt2DArray;
import static common.PathConstructor.getInputPath;

public class Day10 {
    private static final int END_VALUE = 9;

    public static void main(String[] args) {
        int[][] topoMap = fileToInt2DArray(getInputPath(Day10.class));

        //        Find the starting points, 0, count number of unique 9s reached for every 0
        int trailHeadScorePart1 = 0;
        int trailHeadScorePart2 = 0;
        for (int row = 0; row < topoMap.length; row++) {
            for (int col = 0; col < topoMap[row].length; col++) {
                if(topoMap[row][col] == 0) {
                    trailHeadScorePart1 += findTrailHeadScoreUniqueEnd(topoMap, row, col);
                    trailHeadScorePart2 += findTrailHeadScoreUniquePath(topoMap, row, col, 1);
                }
            }
        }
        System.out.println("Day 10, Part 1, Sum of trailhead scores: " + trailHeadScorePart1);
        System.out.println("Day 10, Part 2, Sum of trailhead scores: " + trailHeadScorePart2);
    }

//    Recursive method, but unique cordMemory
    private static int findTrailHeadScoreUniqueEnd(int[][] topoMap, int row, int col) {
        Set<String> endPointMemory = new HashSet<>();
        return findTrailHeadScoreUniqueEnd(endPointMemory, topoMap,row, col, 1);
    }

//    Recursive method that finds the trailhead scores by traversing and checking if end is unique
    public static int findTrailHeadScoreUniqueEnd(Set<String> endPointMemory, int[][] topoMap, int row, int col, int nextValue) {
        if (topoMap[row][col] == END_VALUE) {
            if (endPointMemory.contains("[" +row + ", " + col + "]")) return 0;
            endPointMemory.add("[" +row + ", " + col + "]");
            return 1;
        }

        int trailHeadScore = 0;
        for (Direction direction : Direction.values()) {
            if (!direction.isCardinal()) continue;
            int checkRow = row + direction.getDeltaX();
            int checkCol = col + direction.getDeltaY();

            if (!rangeCheck(topoMap, checkCol, checkRow)) continue;
            if (topoMap[checkRow][checkCol] == nextValue) {
                trailHeadScore += findTrailHeadScoreUniqueEnd(endPointMemory, topoMap, checkRow, checkCol, nextValue + 1);
            }
        }
        return trailHeadScore;
    }

//    Recursive method that finds the trailhead scores by traversing and checking if path is unique
    public static int findTrailHeadScoreUniquePath(int[][] topoMap, int row, int col, int nextValue) {
        if (topoMap[row][col] == END_VALUE) return 1;

        int trailHeadScore = 0;
        for (Direction direction : Direction.values()) {
            if (!direction.isCardinal()) continue;
            int checkRow = row + direction.getDeltaX();
            int checkCol = col + direction.getDeltaY();

            if (!rangeCheck(topoMap, checkCol, checkRow)) continue;
            if (topoMap[checkRow][checkCol] == nextValue) {
                trailHeadScore += findTrailHeadScoreUniquePath(topoMap, checkRow, checkCol, nextValue + 1);
            }
        }
        return trailHeadScore;
    }
}
