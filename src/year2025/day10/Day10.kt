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

fun part1(machineList: List<Machine>): Int {
    var minTotalButtonPresses = 0

    for (machine in machineList) {
        var minNumPresses = 6
        val lights = MutableList(machine.lightDiagram.size) { false }

        for (index in machine.buttons.indices) {
            val numPresses = pressButtons(machine, lights,minNumPresses, pressButton = index )
            if (numPresses < minNumPresses) minNumPresses = numPresses
        }
        minTotalButtonPresses += minNumPresses
    }
    return minTotalButtonPresses
}

fun pressButtons(machine: Machine, lights: MutableList<Boolean>, previousMin: Int, pressButton: Int, numberOfPresses: Int = 0): Int {
    if (lights == machine.lightDiagram || numberOfPresses > previousMin) return numberOfPresses
    val newLights = lights.toMutableList()

    machine.buttons[pressButton].forEach { newLights[it] = !newLights[it] }
    var lowestNumPresses = 100
    for (index in machine.buttons.indices) {
        val numPresses = pressButtons(machine, newLights, previousMin, index, numberOfPresses + 1)
        if (numPresses < lowestNumPresses) lowestNumPresses = numPresses
    }
    return lowestNumPresses
}

data class Machine(val lightDiagram: List<Boolean>, val buttons: List<List<Int>>, val joltages: List<Int>)
