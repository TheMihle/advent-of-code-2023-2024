package year2025.day03

import java.util.Collections.max
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/year2025/day03/input.txt").readLines()

    // Convert to List<List<Int>>
    val convertedInput = input.asSequence().map { it -> it.toList().map { it.digitToInt() } }.toList()

    val joltsPart1 = calculateJolts(convertedInput, 2)
    val joltsPart2 = calculateJolts(convertedInput, 12)

    println("Day 3:")
    println("Part 1, Result: $joltsPart1")
    println("Part 2, Result: $joltsPart2")
}

// Takes in list of the battery banks and calculates max jolts based on number of banks needed.
private fun calculateJolts(input: List<List<Int>>, numberOfBanks: Int): Long {
    var totalJolts = 0L
    for (list in input) {
        var nextFrom = 0
        var jolts= ""
        for (banksLeft in numberOfBanks  downTo 1) {
            val sublist = list.subList(nextFrom, list.size - banksLeft + 1)
            val value = max(sublist)
            nextFrom += sublist.indexOf(value) + 1
            jolts += value.toString()
        }

        totalJolts += jolts.toLong()
    }

    return totalJolts
}