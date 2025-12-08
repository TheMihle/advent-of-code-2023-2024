package year2025.day02

import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.ceil

fun main() {
    val input = Path("src/year2025/day02/input.txt").readText().split(",")
    // To 2D Long array.
    val ranges = input.asSequence().map { it -> it.split("-").map { it.toLong() }}.toList()

    val sumPart1 = part1(ranges)
    val sumPart2 = part2(ranges)

    println("Day 2, Part 1, Sum of invalid IDs: $sumPart1" )
    println("Day 2, Part 2, Sum of invalid IDs: $sumPart2" )
}

// Divides the number in two and checks if two parts is equal. Sums together if true.
// Faster than part 2 regex solution
fun part1(ranges: List<List<Long>>): Long {
    var sumOfIds = 0L
    for (range in ranges) {
        for (number in range.first()..range.last()){
            val string = number.toString()
            // Split in half and compare.
            val split = string.chunked(ceil(string.length.toDouble()/2).toInt())

            if (split.size < 2) continue
            if (split.first() == split.last()) {
                sumOfIds += number
            }
        }
    }
    return sumOfIds
}

// Uses regex to a check if a value is repetition of the same combination of digits 2+ times.
// Sums together if true.
fun part2(ranges: List<List<Long>>): Long {
    var sumOfIds = 0L
    for (range in ranges) {
        for (number in range.first()..range.last()){
            val string = number.toString()
            if (string matches "(\\d+)\\1+".toRegex()) {
                sumOfIds += number
            }
        }
    }

    return sumOfIds
}
