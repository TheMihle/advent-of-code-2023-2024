package year2024.day08;

import java.awt.*;
import java.util.*;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;
import static year2024.day08.Day08.calcAntinodes;

public class Day08WithoutHashmap {
    public static void main(String[] args) {
        String[][] antennaMap = fileTo2DArray(getInputPath(Day08WithoutHashmap.class));

        System.out.println("Day 8, Part 1, Number of unique antinodes: " + part1(antennaMap));
        System.out.println("Day 8, Part 2, Number of unique antinodes: " + part2(antennaMap));
    }

    public static int part1(String[][] antennaMap) {
        Set<Point> antinodes = new HashSet<>();

//        Add cords to hashset based on antenna
        for (int row1 = 0; row1 < antennaMap.length; row1++) {
            for (int col1 = 0; col1 < antennaMap[row1].length; col1++) {
                if (!antennaMap[row1][col1].equals(".")) {
                    findAntinodes(antennaMap, antinodes, row1, col1);
                }
            }
        }
        return antinodes.size();
    }

    public static int part2(String[][] antennaMap) {
        Set<Point> antinodes = new HashSet<>();

//        Add cords to hashmap based on antenna
        for (int row1 = 0; row1 < antennaMap.length; row1++) {
            for (int col1 = 0; col1 < antennaMap[row1].length; col1++) {
                if (!antennaMap[row1][col1].equals(".")) {
                    findRepeatingAntinodes(antennaMap, antinodes, row1, col1);
                }
            }
        }
        return antinodes.size();
    }

//    Find all antinodes and add to Set
    private static void findAntinodes(String[][] antennaMap, Set<Point> antinodes, int row1, int col1) {
        for (int row2 = 0; row2 < antennaMap.length; row2++) {
            for (int col2 = 0; col2 < antennaMap[row1].length; col2++) {
                if ( row1 == row2 && col1 == col2) continue;
                if (antennaMap[row1][col1].equals(antennaMap[row2][col2])) {
                    Point antinode = new Point(col1 + (col1 - col2), row1 + (row1 - row2));
                    if (rangeCheck(antennaMap, antinode.x, antinode.y)) antinodes.add(antinode);
                }
            }
        }
    }

//    Find all repeating antinodes and add to Set
    private static void findRepeatingAntinodes(String[][] antennaMap, Set<Point> antinodes, int row1, int col1) {
        for (int row2 = 0; row2 < antennaMap.length; row2++) {
            for (int col2 = 0; col2 < antennaMap[row1].length; col2++) {
                if ( row1 == row2 && col1 == col2) continue;
                if (antennaMap[row1][col1].equals(antennaMap[row2][col2])) {
                    calcAntinodes(antinodes, antennaMap, new Point(col1, row1), col1 - col2, row1 - row2);
                }
            }
        }
    }


}
