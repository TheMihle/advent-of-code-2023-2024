package year2024.day09;

import java.util.*;

import static year2024.day09.Day09.calcCheckSum;
import static year2024.day09.Day09.reconstructDisk;

public class Day09Part2 {
    public static long part2(int[] diskMap) {
        //        Store index of start of each block
        Map<Integer, Integer> blockStartIndex = CreateBlockStartIndex(diskMap);

        List<Integer> disk = reconstructDisk(diskMap);
        fragmentDisk(disk, diskMap, blockStartIndex);

//        Calculate checksum
        return calcCheckSum(disk);
    }

    private static Map<Integer, Integer> CreateBlockStartIndex(int[] inputArray) {
        Map<Integer, Integer> blockStartIndex= new HashMap<>();
        int blockNumber = 0;
        int spaceNumber = 0;
        for (int i = 0; i < inputArray.length; i = i + 2) {
            blockStartIndex.put(blockNumber++, spaceNumber);
            if (i < inputArray.length - 1) spaceNumber = spaceNumber + inputArray[i] + inputArray[i + 1];
        }

        return blockStartIndex;
    }

//    Moves blocks from the end of the disk, to the first free space on disk
    private static void fragmentDisk(List<Integer> disk, int[] diskMap, Map<Integer, Integer> blockStartIndex) {
        int lastBlockNumber = disk.getLast();
        for (int blockNumber = lastBlockNumber; blockNumber >= 0 ; blockNumber--) {
            int requiredSpace = diskMap[blockNumber * 2];
            int startEmptyIndex = findSpace(disk, requiredSpace);
            if (startEmptyIndex < blockStartIndex.get(blockNumber)) {
                moveBlock(disk, blockStartIndex.get(blockNumber), startEmptyIndex);
            }
        }
    }

//    Finds first index on disk where there are available number of spaces in a row
    private static int findSpace(List<Integer> disk, int numberOfSpaces) {
        int startIndex = -1;

        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == null) {
                if (startIndex == -1) startIndex = i;
            } else if (startIndex != -1){
                if (i - startIndex >= numberOfSpaces) return startIndex;
                startIndex = -1;
            }
        }

        return startIndex;
    }

    private static void moveBlock(List<Integer> disk, int getFromIndex, int fillFromIndex) {
        int blockNumber = disk.get(getFromIndex);
        while (getFromIndex < disk.size() && disk.get(fillFromIndex) == null &&
                disk.get(getFromIndex) != null && disk.get(getFromIndex).equals(blockNumber)) {
            disk.set(fillFromIndex++, disk.get(getFromIndex));
            disk.set(getFromIndex++, null);
        }
    }
}
