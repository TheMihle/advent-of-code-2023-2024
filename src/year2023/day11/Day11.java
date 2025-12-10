package year2023.day11;

import org.jetbrains.annotations.NotNull;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

public class Day11 {
    public static void main(String[] args) {
        String[][] spaceCoordinates = fileTo2DArray(getInputPath(Day11.class));

//        Arrays for columns and rows where space has expanded
        List<Integer> expSpaceX = new ArrayList<>();
        List<Integer> expSpaceY = new ArrayList<>();

//        Finds where space is expanded
        findHorizontalExpansion(spaceCoordinates, expSpaceX);
        findVerticalExpansion(spaceCoordinates, expSpaceY);

//        Creates array with coordinates of the galaxies
        List<Point> galaxyCoordinates = getGalaxtCoordslist(spaceCoordinates);

//        Calculates distance between all galaxies in array
        long lengthSumPart1 = calcuDistanceGalaxies(galaxyCoordinates, expSpaceY, expSpaceX, 1);
        long lengthSumPart2 = calcuDistanceGalaxies(galaxyCoordinates, expSpaceY, expSpaceX, 999999);

        System.out.println("Day 11, Part 1, Sum of lengths: " + lengthSumPart1);
        System.out.println("Day 11, Part 2, Sum of lengths: " + lengthSumPart2);
    }

//    Finds where space is expanded horizontally
    private static void findHorizontalExpansion(String[][] spaceCoordinates, List<Integer> expSpaceX) {
        for (int x = 0; x < spaceCoordinates[0].length; x++) {
            boolean galaxies = false;
            for (String[] rowArray : spaceCoordinates) {
                if (rowArray[x].equals("#")) {
                    galaxies = true;
                    break;
                }
            }
//            If empty column, saves it to array
            if (!galaxies) {
                expSpaceX.add(x);
            }
        }
    }

//    Finds where space is expanded vertically
    private static void findVerticalExpansion(String[][] spaceCoordinates, List<Integer> expSpaceY) {
        for (int y = 0; y < spaceCoordinates.length; y++) {
            boolean galaxies = false;
            for (int x = 0; x < spaceCoordinates[y].length; x++) {
                if (spaceCoordinates[y][x].equals("#")) {
                    galaxies = true;
                    break;
                }
            }
//            If empty row, saves it to array
            if (!galaxies) {
                expSpaceY.add(y);
            }
        }
    }

//    Creates array with coordinates of the galaxies
    @NotNull
    private static List<Point> getGalaxtCoordslist(String[][] spaceCoordinates) {
        List<Point> galaxyCoordinates = new ArrayList<>();

        for (int y = 0; y < spaceCoordinates.length; y++) {
            for (int x = 0; x < spaceCoordinates[y].length; x++) {
                if (spaceCoordinates[y][x].equals("#")){
                    galaxyCoordinates.add(new Point(x,y));
                }
            }
        }
        return galaxyCoordinates;
    }

//    Calculates distance between all galaxies in array
    private static long calcuDistanceGalaxies(List<Point> galaxyCoordinates, List<Integer> expSpaceY, List<Integer> expSpaceX, int extraSpaceAmount) {
        long lengthSum = 0L;
        for (int i = 0; i < galaxyCoordinates.size(); i++) {
            for (int j = i+1; j < galaxyCoordinates.size(); j++) {

//                Adds extra length for every expanded space line it crosses.
                int extraSpace = 0;
//                Optimization
                int yFirstGalaxy = (int) galaxyCoordinates.get(i).getY();
                int ySecondGalaxy = (int) galaxyCoordinates.get(j).getY();
                int xFirstGalaxy = (int) galaxyCoordinates.get(i).getX();
                int xSecondGalaxy = (int) galaxyCoordinates.get(j).getX();

                for (Integer yExpSpace : expSpaceY) {
                    if ((yExpSpace < ySecondGalaxy && yExpSpace > yFirstGalaxy)) {
                        extraSpace += extraSpaceAmount;
                    }
                }
                for (Integer xExpSpace : expSpaceX) {
                    if ((xExpSpace > xSecondGalaxy && xExpSpace < xFirstGalaxy) ||
                            (xExpSpace < xSecondGalaxy && xExpSpace > xFirstGalaxy)) {
                        extraSpace+= extraSpaceAmount;
                    }
                }

//                Calculates sum
                lengthSum += Math.abs(yFirstGalaxy-ySecondGalaxy) + Math.abs(xFirstGalaxy-xSecondGalaxy) + extraSpace;
            }
        }
        return lengthSum;
    }
}
