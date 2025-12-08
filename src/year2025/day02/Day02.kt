package year2025.day02

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.ceil

fun main() {
    val input = Path("src/year2025/day02/input.txt").readText().split(",")
    // To 2D Long array.
    val ranges = input.asSequence().map { it -> it.split("-").map { it.toLong() }}.toList()

    part1(ranges)
    part2(ranges)
}

fun part1(ranges: List<List<Long>>) {
    var result = 0L
    for (range in ranges) {
        for (number in range.first()..range.last()){
            val string = number.toString()
            // Split in half and compare.
            val split = string.chunked(ceil(string.length.toDouble()/2).toInt())

            if (split.size < 2) continue
            if (split.first() == split.last()) {
                result += number
            }
        }
    }

    println("Day 2, Part 1, Sum of invalid IDs: $result" )
}

fun part2(ranges: List<List<Long>>) {

}





