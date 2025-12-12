package year2025.day09


import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val input = Path("src/year2025/day09/input.txt").readLines()
    // Convert to a list of pairs of Ints
    val convertedCoords = input.map { it -> it.split(",").map { it.toInt() } }
                               .map { Pair(it.first(), it.last()) }

    println("Day 9, Part 1, Largest area: ${part1(convertedCoords)}")
}

// Calculates the largest area where two points is the corners.
fun part1(coords: List<Pair<Int, Int>>): Long {
    var largestArea = 0L

    for (coord1 in coords) {
        for (coord2 in coords) {
            val height = abs(coord1.first - coord2.first) + 1
            val width = abs(coord1.second - coord2.second) + 1
            val area = height.toLong() * width.toLong()

            largestArea = max(largestArea, area)
        }
    }
    return largestArea
}