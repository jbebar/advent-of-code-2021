package org.jbebar.aoc21.day2

import Day
import Reader.readInputLines

class Day2A : Day {

    override fun run(): Long {
        val inputs = retrieveBits()

        val horizontalPosition = inputs.filter {
            it.first == "forward"
        }.sumOf {
            it.second
        }

        val depth = inputs.fold(0) { acc, p ->
            when (p.first) {
                "down" -> acc + p.second
                "up" -> acc - p.second
                else -> acc
            }
        }

        return (depth * horizontalPosition).toLong()
    }

}


class Day2B : Day {

    class Coordinates(val aim: Int, val xPosition: Int, val depth: Int)

    override fun run(): Long {
        val inputs = retrieveBits()
        val result = inputs.fold(Coordinates(0, 0, 0)) { acc, (direction, value) ->

            val nextAcc = when (direction) {
                "down" -> Coordinates(acc.aim + value, acc.xPosition, acc.depth)
                "up" -> Coordinates(acc.aim - value, acc.xPosition, acc.depth)
                "forward" -> {
                    Coordinates(acc.aim, acc.xPosition + value, acc.depth + value * acc.aim)
                }
                else -> acc
            }
            nextAcc
        }
        println("Result 2B ${result.xPosition * result.depth}")
        return (result.xPosition * result.depth).toLong()
    }

}

private fun retrieveBits() = readInputLines("Day2.txt").map {
    it.split(" ")
}.map {
    it.first() to it[1].toInt()
}

