package year2025.day10

import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    val input = Path("src/year2025/day10/input.txt").readLines()
    val machines = input.map { machine -> machine.split(" ").map { panel ->
                panel.replace(Regex("[{\\[()\\]}]"), "") }.toMutableList()}

    val machineList = machines.map { machine ->
        Machine(lightDiagram = machine.removeFirst().toCharArray().map { it == '#' },
            joltages = machine.removeLast().split(",").map { it.toInt() },
            buttons = machine.map{ button -> button.split(",").map { it.toInt() } }) }

    println("Day 10, Part 1, Minimum numer of button presses required: ${part1(machineList)}")
}

// TODO: May be able to speed up more by using just byte like "0110" instead of a list
fun part1(machineList: List<Machine>): Int {
    var minTotalButtonPresses = 0

    for (machine in machineList) {
        val minNumPresses = 10
        val memory = mutableMapOf<List<Boolean>, Int>()
        val lights = MutableList(machine.lightDiagram.size) { false }

        val numPresses = pressButtons(memory, machine, lights,minNumPresses )
        minTotalButtonPresses += numPresses
    }
    return minTotalButtonPresses
}

// Recursively finds the shortest amount of button presses to get the lightDiagram output. Uses Map as memory between steps.
fun pressButtons(memory: MutableMap<List<Boolean>, Int>, machine: Machine, lights: List<Boolean>, maxDepth: Int, numberOfPresses: Int = 0): Int {
    if (numberOfPresses > maxDepth) return Int.MAX_VALUE
    if (lights == machine.lightDiagram) return 0
    if (memory.contains(lights)) return memory.getValue(lights)

    var lowestPressesFromHere = Int.MAX_VALUE
    for (button in machine.buttons) {
        val newLights = lights.toMutableList()
        button.forEach { newLights[it] = !newLights[it] }

        val numPresses = pressButtons(memory, machine, newLights, maxDepth, numberOfPresses + 1)
        if (numPresses < lowestPressesFromHere) lowestPressesFromHere = numPresses
    }
    if (lowestPressesFromHere == Int.MAX_VALUE) return Int.MAX_VALUE
    memory[lights] = lowestPressesFromHere + 1
    return lowestPressesFromHere + 1
}

data class Machine(val lightDiagram: List<Boolean>, val buttons: List<List<Int>>, val joltages: List<Int>)
