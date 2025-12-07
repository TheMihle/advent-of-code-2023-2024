package year2025.day05

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/year2025/day05/input.txt").readLines()

    // Converts ID ranges to List<List<Int>> and ingredientIDs to List<Int>
    val freshIds = input.subList(0, input.indexOf(""))
        .map { it -> it.split("-").map { it.toLong() } }
    val ingredientIds = input.subList(input.indexOf("") + 1, input.size).map { it.toLong() }

    part1(freshIds, ingredientIds)
}


fun part1(freshIds: List<List<Long>>, ingredientIds: List<Long>) {
    var numberOfFreshIngredientIds = 0
    for (ingredientId in ingredientIds) {
        for (freshId in freshIds) {
            if (ingredientId in freshId.first()..freshId.last()) {
                numberOfFreshIngredientIds++
                break
            }
        }
    }

    println("Day 5, Part 1, Number of fresh ingredient IDs: $numberOfFreshIngredientIds")
}