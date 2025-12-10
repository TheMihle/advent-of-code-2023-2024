package year2023.day02;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static common.ImportFile.fileToArray;
import static common.PathConstructor.getInputPath;

public class Day02 {
    public static void main(String[] args) {
        List<String> inputLines = fileToArray(getInputPath(Day02.class));

        System.out.println("Day 2, Part 1, Calculated sum of IDs: " + part1(inputLines));
        System.out.println("Day 2, Part 2, Sum of the set power: " + part2(inputLines));
    }

    public static int part1(List<String> inputLines) {
//        Pattern for what to find
        Pattern colorPattern = Pattern.compile("red|green|blue");
        Pattern numberPattern = Pattern.compile("\\d+");

//        Max number to look for
        int maxRed = 12;
        int maxGreen = 13;
        int maxBlue = 14;

        int idSum = 0;

        for (int i = 0; i < inputLines.size(); i++) {
            String[] colorCubeArray = inputLines.get(i).split(": ")[1]
                                                       .split("[;,]");
            boolean overMax = false;

//            Finds games with too many of a coloured cube
            for (String cube : colorCubeArray) {
                Matcher matcher = colorPattern.matcher(cube);
                matcher.find();

//                Red cubes
                if (matcher.group().equals("red")){
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > maxRed) {
                        overMax = true;
                    }

//                    Green Cubes
                } else if (matcher.group().equals("green")) {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > maxGreen) {
                        overMax = true;
                    }

//                    Blue Cubes
                } else {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                     if (Integer.parseInt(matcher.group()) > maxBlue) {
                         overMax = true;
                   }
                }
            }

//            Calculates sum
            if (!overMax){ idSum += i+1; }
        }
        return idSum;
    }

    public static int part2(List<String> inputLines) {
        //        Pattern for what to find
        Pattern colorPattern = Pattern.compile("red|green|blue");
        Pattern numberPattern = Pattern.compile("\\d+");

        int powerSum = 0;

        for (String inputLine : inputLines) {
            String[] colorCubeArray = inputLine.split(": ")[1]
                                               .split("[;,]");

//                Highest number of each colour found
                int redHigh = 0;
                int greenHigh = 0;
                int blueHigh = 0;

//                Finds highest number of each colour found
            for (String cube : colorCubeArray) {
                Matcher matcher = colorPattern.matcher(cube);
                matcher.find();

//                Red cubes
                if (matcher.group().equals("red")) {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > redHigh) {
                        redHigh = Integer.parseInt(matcher.group());
                    }

//                    Green Cubes
                } else if (matcher.group().equals("green")) {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > greenHigh) {
                        greenHigh = Integer.parseInt(matcher.group());
                    }

//                    Blue Cubes
                } else {
                    matcher = numberPattern.matcher(cube);
                    matcher.find();
                    if (Integer.parseInt(matcher.group()) > blueHigh) {
                        blueHigh = Integer.parseInt(matcher.group());
                    }
                }
            }

            powerSum += redHigh * greenHigh * blueHigh;
        }
        return powerSum;
    }
}
