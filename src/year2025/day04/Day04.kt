package year2025.day04

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/year2025/day04/input.txt").readLines()
    // Convert to a 2D list and add padding
    val map = input.map { it.split("").toMutableList() }.toMutableList()
    
    map.addFirst((1..map.first().size).map { "." }.toMutableList())     // Top
    map.addLast((1..map.first().size).map { "." }.toMutableList())      // Bottom
    map.forEach { it[0] = "."; it[it.lastIndex] = "."; }                              // Sides

    println("Day 4:")
    println("Part 1, Number of rolls that can be accessed: ${part1(map)}")
    println("Part 2, Number of rolls that can be removed: ${part2(map)}")

    map.forEach { println(it) }
}

// Count number of rolls that can be moved without removing any.
private fun part1(map: MutableList<MutableList<String>>): Int {
    return removeRolls(map, checkMode = true)
}

// Count number of rolls that can be removed with as many passes as needed.
private fun part2(map: MutableList<MutableList<String>>): Int{
    var totalRollsRemoved = 0
    var rollRemoved = true
    while (rollRemoved) {
        val rollsRemoved = removeRolls(map)
        totalRollsRemoved += rollsRemoved
        if (rollsRemoved < 1) rollRemoved = false
    }
    return totalRollsRemoved
}

// Removes or checks for rolls to be removed. Remove(default) mode require multiple passes.
private fun removeRolls(map: MutableList<MutableList<String>>,  checkMode: Boolean = false): Int {
    var numRemoved = 0
    for (y in 1..<map.lastIndex) {
        for (x in 1..<map.first().lastIndex) {
            if (map[y][x] == "@" && countNearbyRolls(map, x, y) < 4) {
                if (!checkMode) map[y][x] = "."
                numRemoved++
            }
        }
    }
    return numRemoved
}

// Counts number of rolls "@" in the 3x3 field around the coordinates, ignores the input roll coordinate.
private fun countNearbyRolls(map: MutableList<MutableList<String>>, x: Int, y: Int): Int {
    var numberOfRolls = 0
    for (iy in (y - 1)..(y + 1)) {
        for (ix in (x - 1)..(x + 1)) {
            if (map[iy][ix] == "@") numberOfRolls++
        }
    }
    return numberOfRolls - 1
}