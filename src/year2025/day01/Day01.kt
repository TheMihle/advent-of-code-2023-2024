package year2025.day01

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/year2025/day01/input.txt").readLines()

    // Convert to List of pairs
    val convertedInput = input.map { Pair(it.substring(0,1), it.substring(1).toInt()) }

    part1(convertedInput)
    part2(convertedInput)
}

// Count number of times the position
fun part1(rotations: List<Pair<String, Int>>) {
    var position = 50
    var numberOfZeroes = 0
    for (rotation in rotations) {
        if (rotation.first == "R") {
            position = (position + rotation.second).mod(100)
            if (position == 0) numberOfZeroes++
        } else if (rotation.first == "L") {
            position = (position - rotation.second).mod(100)
            if (position == 0) numberOfZeroes++
        }
    }
    println("Day 1, Part 1, Number of times pointing at 0: $numberOfZeroes")
}

fun part2(rotations: List<Pair<String, Int>>) {

}