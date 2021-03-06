package org.jbebar.aoc21.day1

import Day
import Reader.readInputLines

class Day1A : Day {

    override fun run(): Long {

        // For loops way

        val inputMeasurements = readInputLines("Day1.txt").map { it.toInt() }
        var previousMeasurement: Int? = null
        var resultForLoopWay = 0
        for (currentMeasurement in inputMeasurements.toList()) {
            if (previousMeasurement != null && previousMeasurement < currentMeasurement) {
                resultForLoopWay++
            }
            previousMeasurement = currentMeasurement
        }
        println("Result 1A $resultForLoopWay")

        // Functional way

        val resultFunctionalWay = inputMeasurements.windowed(2).count { e -> e[0] < e[1] }

        check(resultFunctionalWay == resultForLoopWay)
        return resultForLoopWay.toLong()
    }

}

class Day1B : Day {


    override fun run(): Long {

        // For loops way

        val inputMeasurements = readInputLines("Day1.txt").map { it.toInt() }
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

