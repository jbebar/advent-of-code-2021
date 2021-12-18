package org.jbebar.aoc21.day1

import Day
import Reader.readInputs

class Day1A : Day {

    override fun run(): Long {

        // For loops way

        val inputMeasurements = readInputs("Day1.txt").map { it.toInt() }
        var previousMeasurement: Int? = null
        var result = 0
        for (currentMeasurement in inputMeasurements.toList()) {
            if (previousMeasurement != null && previousMeasurement < currentMeasurement) {
                result++
            }
            previousMeasurement = currentMeasurement
        }
        println("Result 1A $result")

        // Functional way

        val resultFunctionalWay = inputMeasurements.windowed(2).count { e -> e[0] < e[1] }
        return result.toLong()
    }

}

class Day1B : Day {


    override fun run(): Long {

        // For loops way

        val inputMeasurements = readInputs("Day1.txt").map { it.toInt() }
        var previousMeasurement: Int? = null
        var result = 0

        val nextMeasurements = mutableListOf<Int>()
        for (i in 2 until inputMeasurements.size) {
            val windowSum = inputMeasurements[i - 2] + inputMeasurements[i - 1] + inputMeasurements[i]
            nextMeasurements.add(windowSum)
        }

        for (currentMeasurement in nextMeasurements) {
            if (previousMeasurement != null && previousMeasurement < currentMeasurement) {
                result++
            }
            previousMeasurement = currentMeasurement
        }

        println("Result 1B $result")

        // Functional way

        val resultFunctionalWay = inputMeasurements.windowed(3, 1).map { it.sum() }.windowed(2).count { e -> e[0] < e[1] }
        println("Result 1B $resultFunctionalWay")
        return result.toLong()
    }

}

