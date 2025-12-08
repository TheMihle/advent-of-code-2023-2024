package year2024.day09;

import java.util.ArrayList;
import java.util.List;

import static common.ImportFile.fileToSplittedString;
import static common.PathConstructor.getInputPath;
import static year2024.day09.Part_2.part2;

public class Day09 {
    public static void main(String[] args) {

//        Import file as array and convert to int array
        String[] inputDiskMap = fileToSplittedString(getInputPath(Day09.class));

        int[] diskMap = new int[inputDiskMap.length];
        for (int i = 0; i < inputDiskMap.length; i++) {
            diskMap[i] = Integer.parseInt(inputDiskMap[i]);
        }

        System.out.println("Day 9, Part 1, Disk checksum: " + part1(diskMap));
        System.out.println("Day 9, Part 2, Disk checksum: " + part2(diskMap));
    }

    public static long part1(int[] diskMap) {
        List<Integer> disk = reconstructDisk(diskMap);
        fragmentDisk(disk);

//        Calculate checksum
        return calcCheckSum(disk);
    }

//    Reconstruct disk from diskMap
    public static List<Integer> reconstructDisk(int[] diskMap) {
        List<Integer> disk = new ArrayList<>();
        int nextBlockId = 0;

        for (int i = 0; i < diskMap.length; i++) {
            for (int j = 0; j < diskMap[i]; j++) {
                if (i % 2 == 0) disk.add(nextBlockId);
                else disk.add(null);
            }
            if (i % 2 == 0) nextBlockId++;
        }
        return disk;
    }

    // Calculate checksum
    public static long calcCheckSum(List<Integer> disk) {
        long checkSum = 0;
        for (int i = 0; i < disk.size(); i++) {
            if ( disk.get(i) != null) checkSum += (long) i * disk.get(i);
        }
        return checkSum;
    }

//    Fill empty spaces on disk from the end of the disk
    private static void  fragmentDisk(List<Integer> disk) {
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == null) {
                while (disk.getLast() == null) disk.removeLast();
                disk.set(i, disk.removeLast());
            }
        }
    }
}
