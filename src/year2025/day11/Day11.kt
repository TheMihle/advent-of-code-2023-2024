package year2025.day11

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.time.measureTime

fun main() {
    val input = Path("src/year2025/day11/input.txt").readLines()
    val serverMap = input.map { it.replace(":", "").split(" ").toMutableList() }
                         .associate { Pair(it.removeFirst(), it.toList()) }

    println("Day 11:")
    println("Part 1, Number of paths from server you to out: ${part1(serverMap)}")
    println("Part 2, Number of paths from server svr to out, via dac and fft: ${part2(serverMap)}")

    val times = 1000
    val timePart1 = measureTime {
        repeat(times) { part1(serverMap)}
    }
    println("Part 1, average time over $times loops: ${timePart1/times}")

    val timePart2 = measureTime {
        repeat(times) { part2(serverMap)}
    }
    println("Part 2, average time over $times loops: ${timePart2/times}")
}

// Count how many paths there are from the server "you" to server "out".
fun part1(serverMap: Map<String, List<String>>): Long {
    return countPaths(serverMap, "you", "out")
}

// Count how many paths there are from the server "svr" to server "out",
// that also passes through server "fft" and "dac"
fun part2(serverMap: Map<String, List<String>>): Long {
    var numberOfPaths = 1L
    numberOfPaths *= countPaths(serverMap,"svr", "fft")
    numberOfPaths *= countPaths(serverMap,"fft", "dac")
    numberOfPaths *= countPaths(serverMap,"dac", "out")
    return numberOfPaths
}

// Count how many paths there are from one server to another.
// Memorization to not do unnecessary work/optimisation.
private fun countPaths(serverMap: Map<String, List<String>>, fromServer: String, toServer: String, memory: MutableMap<String, Long> = mutableMapOf()): Long {
    if (fromServer == toServer) return 1
    if (fromServer == "out") return 0
    if (memory.contains(fromServer)) return memory.getValue(fromServer)

    var numberOfPaths = 0L
    val serverOutputs = serverMap[fromServer]!!
    for (output in serverOutputs) {
        numberOfPaths += countPaths(serverMap, output, toServer, memory)
    }
    memory[fromServer] = numberOfPaths
    return numberOfPaths
}

