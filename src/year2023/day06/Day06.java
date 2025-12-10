package year2023.day06;

import java.util.List;

import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Day06 {
    public static void main(String[] args) {

        System.out.println("Day 6, Part 1, Sum: " + part1());
        System.out.println("Day 6, Part 2, Number of ways to beat the record: " + part2());
    }

    public static int part1() {
            List<String> inputLines = fileToArray(getInputPath(Day06.class));

//        Removes everything except the numbers and one space in between and then  splits and converts to int arrays
        for (int i = 0; i < inputLines.size(); i++) {
            inputLines.set(i, inputLines.get(i).replaceFirst(".+:", ""));
            inputLines.set(i, inputLines.get(i).trim().replaceAll(" +", " "));
        }

        String[] sTimeArray = inputLines.get(0).split(" ");
        String[] sDistanceArray = inputLines.get(1).split(" ");

        int[] timeArray = new int[sTimeArray.length];
        int[] distanceArray = new int[sDistanceArray.length];
        for (int i = 0; i < sTimeArray.length; i++) {
            timeArray[i] = Integer.parseInt(sTimeArray[i]);
            distanceArray[i] = Integer.parseInt(sDistanceArray[i]);
        }

        int sum = 1;

        // TODO: 06.12.2023 Maybe use math instead of loop?
//        Calculate how many possible ways to win
        for (int i = 0; i < timeArray.length; i++) {
            int maxTime = 0; int minTime = 0;
            for (int j = timeArray[i]; j > 0; j--) {
                if ((timeArray[i]-j) * j > distanceArray[i]){
                    maxTime = j;
                    break;
                }
            }

            for (int j = 0; j < timeArray[i]; j++) {
                if ((timeArray[i]-j) * j > distanceArray[i]){
                    minTime = j;
                    break;
                }
            }

            sum *= (maxTime-minTime)+1;
        }
        return sum;
    }

    public static int part2() {
        List<String> inputLines = fileToArray(getInputPath(Day06.class));

//        Removes everything except the numbers and converts to int/Long
        for (int i = 0; i < inputLines.size(); i++) {
            inputLines.set(i, inputLines.get(i).replaceFirst(".+:", ""));
            inputLines.set(i, inputLines.get(i).trim().replaceAll(" +", ""));
        }

        int time = Integer.parseInt(inputLines.get(0));
        long distance = Long.parseLong(inputLines.get(1));

        // TODO: 06.12.2023 Maybe use math instead of loops?
//       Calculates max and minimum time
        int maxTime = 0;
        int minTime = 0;
        for (int j = time; j > 0; j--) {
            if ((long) (time - j) * j > distance) {
                maxTime = j;
                break;
            }
        }

        for (int j = 0; j < time; j++) {
            if ((long) (time - j) * j > distance) {
                minTime = j;
                break;
            }
        }

        return (maxTime - minTime + 1);
    }
}
