package year2024.day08;

import java.awt.*;
import java.util.*;
import java.util.List;

import static common.DataStructureUtils.rangeCheck;
import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

/*
    Storing antenna coords in map and then calculate antinodes with that, is extra code, than double looping,
    but is also faster and easier to split into methods
*/
public class Day08 {
    public static void main(String[] args) {
        String[][] antennaMap = fileTo2DArray(getInputPath(Day08.class));
        Map<String, List<Point>> antennaCoords = new HashMap<>();

//        Add antenna coords to hashmap based on antenna frequency
        addAllAntennaCoords(antennaMap, antennaCoords);

        System.out.println("Day 8, Part 1, Number of unique antinodes: " + part1(antennaMap, antennaCoords));
        System.out.println("Day 8, Part 2, Number of unique antinodes: " + part2(antennaMap, antennaCoords));
    }

    public static int part1(String[][] antennaMap, Map<String, List<Point>> antennaCoords) {
        Set<Point> antinodes = new HashSet<>();

//        Find all antinodes and add to Set
        findAntinodes(antennaMap, antennaCoords, antinodes);
        return antinodes.size();
    }

    public static int part2(String[][] antennaMap, Map<String, List<Point>> antennaCoords) {
        Set<Point> antinodes = new HashSet<>();

//        Find all antinodes and add to Set
        findRepeatingAntinodes(antennaMap, antennaCoords, antinodes);
        return antinodes.size();
    }

//    Add antenna coords to hashmap based on antenna frequency
    public static void addAllAntennaCoords(String[][] antennaMap, Map<String, List<Point>> antennaCoords) {
        for (int row = 0; row < antennaMap.length; row++) {
            for (int col = 0; col < antennaMap[row].length; col++) {
                if (!antennaMap[row][col].equals(".")) {
                    antennaCoords.computeIfAbsent(antennaMap[row][col], n -> new ArrayList<>()).add(new Point(col, row));
                }
            }
        }
    }

//    Find all antinodes and add to Set
    private static void findAntinodes(String[][] antennaMap, Map<String, List<Point>> antennaCoords, Set<Point> antinodes) {
        for (List<Point> list : antennaCoords.values()) {
            for (Point antenna1 : list) {
                for (Point antenna2 : list) {
                    if (antenna1.equals(antenna2)) continue;
                    Point antinode = new Point(antenna1.x + (antenna1.x - antenna2.x), antenna1.y + (antenna1.y - antenna2.y));
                    if (rangeCheck(antennaMap, antinode.x, antinode.y)) antinodes.add(antinode);
                }
            }
        }
    }

//    Find all repeating antinodes and add to Set
    private static void findRepeatingAntinodes(String[][] antennaMap, Map<String, List<Point>> antennaCoords, Set<Point> antinodes) {
        for (List<Point> list : antennaCoords.values()) {
            for (Point antenna1 : list) {
                for (Point antenna2 : list) {
                    if (antenna1.equals(antenna2)) continue;
                    calcAntinodes(antinodes, antennaMap, antenna1,
                            antenna1.x - antenna2.x, antenna1.y - antenna2.y);
                }
            }
        }
    }

//    Calculates antinodes based on one pair of antennas
    static void calcAntinodes(Set<Point> antinodes, String[][] antennaMap, Point point, int deltaX, int deltaY) {
        int x = point.x;
        int y = point.y;
        while (rangeCheck(antennaMap, x, y)) {
            antinodes.add(new Point(x, y));
            x += deltaX;
            y += deltaY;
        }
    }
}

