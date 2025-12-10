package year2023.day16;

import static common.ImportFile.fileTo2DArray;
import static common.PathConstructor.getInputPath;

public class Day16 {
    public static void main(String[] args) {
        String[][] spaceCoordinates = fileTo2DArray(getInputPath(Day16.class));

        System.out.println("Day 16, Part 1, Number of energized tiles: " + part1(spaceCoordinates));
        System.out.println("Day 16, Part 2, Highest number of energized tiles: " + part2(spaceCoordinates));
    }

//    Gets number of energized tiles when starting at 0,0
    public static int part1(String[][] spaceCoordinates) {
                int[][][] energizedCoordinates = new int[spaceCoordinates.length][spaceCoordinates[0].length][4];

//        Starts recursive methods to find what coordinates are energized
        moveRight(0, 0, spaceCoordinates, energizedCoordinates, true);

//        Prints energizedCoordinateArray
        for (int[][] energizedCoordinate : energizedCoordinates) {
            for (int[] ints : energizedCoordinate) {
                int coordinateSum = 0;
                for (int anInt : ints) {
                    if (anInt > 0) coordinateSum++;
                }
                System.out.print(coordinateSum + " ");
            }
            System.out.println();
        }

//        Calculate number of energized tiles
        return energizedTilesCount(energizedCoordinates);
    }

//    Gets highest number of energized tiles with any edge starting point
    public static int part2(String[][] spaceCoordinates) {
        int largestSum = 0;

//        Checks number of Energized coordinates when starting from top right
        for (int y = 0; y < spaceCoordinates.length; y++) {
            int[][][] energizedCoordinates = new int[spaceCoordinates.length][spaceCoordinates[0].length][4];

            moveRight(0, y, spaceCoordinates, energizedCoordinates, true);

//        Calculate sum and saves if its largest one yet:
            int localSum = energizedTilesCount(energizedCoordinates);
            if (localSum > largestSum) largestSum = localSum;
        }

//        Checks number of Energized coordinates when starting from left edge
        for (int y = 0; y < spaceCoordinates.length; y++) {
            int[][][] energizedCoordinates = new int[spaceCoordinates.length][spaceCoordinates[0].length][4];

            moveLeft(spaceCoordinates.length - 1, y, spaceCoordinates, energizedCoordinates, true);

//        Calculate sum and saves if its largest one yet:
            int localSum = energizedTilesCount(energizedCoordinates);
            if (localSum > largestSum) largestSum = localSum;
        }

//        Checks number of Energized coordinates when starting from bottom edge
        for (int x = 0; x < spaceCoordinates[0].length; x++) {
            int[][][] energizedCoordinates = new int[spaceCoordinates.length][spaceCoordinates[0].length][4];

            moveDownwards(x, 0, spaceCoordinates, energizedCoordinates, true);

//        Calculate sum and saves if its largest one yet:
            int localSum = energizedTilesCount(energizedCoordinates);
            if (localSum > largestSum) largestSum = localSum;
        }

//        Checks number of Energized coordinates when starting from top edge
        for (int x = 0; x < spaceCoordinates[0].length; x++) {
            int[][][] energizedCoordinates = new int[spaceCoordinates.length][spaceCoordinates[0].length][4];

            moveUpwards(x, spaceCoordinates[0].length - 1, spaceCoordinates, energizedCoordinates, true);

//        Calculate sum and saves if its largest one yet:
            int localSum = energizedTilesCount(energizedCoordinates);
            if (localSum > largestSum) largestSum = localSum;
        }
        return largestSum;
    }

//    Moves downwards in coordinateSystem and energises until symbol is hit, or loop is found
    private static void moveDownwards(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates, boolean first) {

        do {
            if (!first) {
                energizedCoordinates[y][x][3] += 1;
                y++;
            }
            if (y > spaceCoordinates.length - 1) return;
            if (energizedCoordinates[y][x][3] > 1 && energizedCoordinates[y - 1][x][3] > 1) return;
            first = false;
        } while (spaceCoordinates[y][x].equals(".") ||
                spaceCoordinates[y][x].equals("|"));

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "\\" -> moveRight(x, y, spaceCoordinates, energizedCoordinates, false);
            case "/" -> moveLeft(x, y, spaceCoordinates, energizedCoordinates, false);
            case "-" -> {
                moveLeft(x, y, spaceCoordinates, energizedCoordinates, false);
                moveRight(x, y, spaceCoordinates, energizedCoordinates, false);
            }
        }
    }

//    Moves upwards in coordinateSystem and energizes until symbol is hit, or loop is found
    private static void moveUpwards(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates, boolean first) {

        do {
            if (!first) {
                energizedCoordinates[y][x][1] += 1;
                y--;
            }
            if (y == -1) return;
            if (energizedCoordinates[y][x][1] > 1 && energizedCoordinates[y + 1][x][1] > 1) return;
            first = false;
        } while (spaceCoordinates[y][x].equals(".") ||
                spaceCoordinates[y][x].equals("|"));

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "\\" -> moveLeft(x, y, spaceCoordinates, energizedCoordinates, false);
            case "/" -> moveRight(x, y, spaceCoordinates, energizedCoordinates, false);
            case "-" -> {
                moveLeft(x, y, spaceCoordinates, energizedCoordinates, false);
                moveRight(x, y, spaceCoordinates, energizedCoordinates, false);
            }
        }
    }

//    Moves left in coordinateSystem and energizes until symbol is hit, or loop is found
    private static void moveLeft(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates, boolean first) {

        do {
            if (!first) {
                energizedCoordinates[y][x][2] += 1;
                x--;
            }
            if (x < 0) return;
            if (energizedCoordinates[y][x][2] > 1 && energizedCoordinates[y][x + 1][2] > 1) return;
            first = false;
        } while (spaceCoordinates[y][x].equals(".") ||
                spaceCoordinates[y][x].equals("-"));

//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "|" -> {
                moveUpwards(x, y, spaceCoordinates, energizedCoordinates, false);
                moveDownwards(x, y, spaceCoordinates, energizedCoordinates, false);
            }
            case "\\" -> moveUpwards(x, y, spaceCoordinates, energizedCoordinates, false);
            case "/" -> moveDownwards(x, y, spaceCoordinates, energizedCoordinates, false);
        }
    }

//    Moves right in coordinateSystem and energizes until symbol is hit, or loop is found
    private static void moveRight(int x, int y, String[][] spaceCoordinates, int[][][] energizedCoordinates, boolean first) {

        do {
            if (!first) {
                energizedCoordinates[y][x][0] += 1;
                x++;
            }
            if (x > spaceCoordinates[y].length - 1) return;
            if (energizedCoordinates[y][x][0] > 1 && energizedCoordinates[y][x - 1][0] > 1) return;
            first = false;
        } while (spaceCoordinates[y][x].equals(".") ||
                spaceCoordinates[y][x].equals("-"));


//        Calls new method(s) for direction based on symbol
        switch (spaceCoordinates[y][x]) {
            case "|" -> {
                moveUpwards(x, y, spaceCoordinates, energizedCoordinates, false);
                moveDownwards(x, y, spaceCoordinates, energizedCoordinates, false);
            }
            case "\\" -> moveDownwards(x, y, spaceCoordinates, energizedCoordinates, false);
            case "/" -> moveUpwards(x, y, spaceCoordinates, energizedCoordinates, false);
        }
    }

//    Calculates number of energized cells in a 3D coordinate system where the 3D is same location
    private static int energizedTilesCount(int[][][] energizedCoordinates) {
        int number = 0;
        for (int[][] energizedCoordinate : energizedCoordinates) {
            for (int[] ints : energizedCoordinate) {
                boolean coordinateEnergized = false;
                for (int anInt : ints) {
                    if (anInt > 0) {
                        coordinateEnergized = true;
                        break;
                    }
                }
                if (coordinateEnergized) number++;
            }
        }
        return number;
    }
}
