package year2025.day01

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs

fun main() {
    val input = Path("src/year2025/day01/input.txt").readLines()

    // Convert to List of positive or negative steps
    val convertedInput = input.map {
        val step = it.substring(1).toInt()
        if (it.substring(0,1) == "L") -step
        else step
    }

    val passwordPart1 = part1(convertedInput)
    val passwordPart2 = part2(convertedInput)

    println("Day 1, Part 1, Number of times dial is pointing at 0: $passwordPart1")
    println("Day 1, Part 2, Number of times dial is passing 0: $passwordPart2")
}

// Count number of times the position ends at 0
fun part1(rotations: List<Int>): Int {
    var position = 50
    var numberOfZeroes = 0
    for (rotation in rotations) {
        position = (position + rotation).mod(100)
        if (position == 0) numberOfZeroes++
    }
    return numberOfZeroes
}

// Count number of times the position passes 0
fun part2(rotations: List<Int>): Int {
    var position = 50
    var numberOfZeroes = 0
    for (rotation in rotations) {
        val rawPos = position + rotation

        // Count times crossing or exactly 100 or -100
        numberOfZeroes += abs(rawPos) / 100

        // Any 0 or negative rawPos when not starting from 0 means it passed 0 one more time
        if (position > 0 && rawPos <= 0) {
            numberOfZeroes++
        }
        position = rawPos.mod(100)
    }
    return numberOfZeroes
}