package year2025.day11

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/year2025/day11/input.txt").readLines()
    val serverMap = input.map { it.replace(":", "").split(" ").toMutableList() }
                         .associate { Pair(it.removeFirst(), it.toList()) }

    println("Day 11, Part 1, Number of paths from you to out: ${part1(serverMap)}")
}

// Count how many ways to reach paths from the "you" sever.
fun part1(serverMap: Map<String, List<String>>): Int {
    return countPaths(serverMap, "you")
}

// Count how many ways to reach paths from the input server.
private fun countPaths(serverMap: Map<String, List<String>>, server: String): Int {
    if (server == "out") return 1
    var numberOfPaths = 0
    val serverOutputs = serverMap[server]!!

    for (output in serverOutputs) {
        numberOfPaths += countPaths(serverMap, output )
    }
    return numberOfPaths
}
