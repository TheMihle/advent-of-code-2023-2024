package year2025.day06

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/year2025/day06/input.txt").readLines()

    part1(input)
    part2(input)
}

private fun part1(input: List<String>) {
    // Convert to List<List>, extract operators and convert to long
    val splitInput = input.map { it.trim().split("\\s+".toRegex()) }.toMutableList()
    val operators = splitInput.removeLast()
    val convertedInput = splitInput.map { it.map { it -> it.toLong() } }

    var result = 0L
    for (i in 0..convertedInput.first().size - 1) {
        result += reduceListWithOperator(operators[i], convertedInput.map { it[i] })
    }

    println("Day 6, Part 1, Result: $result")
}

private fun part2(input: List<String>) {

    println("PART 2 NOT IMPLEMENTED")
}


private fun reduceListWithOperator(operator: String, elements: List<Long>): Long {
    return when (operator) {
        "*" -> elements.reduce { acc, elem -> acc * elem }
        "+" -> elements.reduce { acc, elem -> acc + elem }
        else -> throw  IllegalArgumentException("Operator not supported")
    }
}