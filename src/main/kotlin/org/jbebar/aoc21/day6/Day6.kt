package org.jbebar.aoc21.day6

import Day
import Reader

class Day6A : Day {

    override fun run(): Long {
        val numberOfDays = 80 // Out of memory error with 256 elements
        var fishTimers = formatInput()
        for (i in (1..numberOfDays)) {
            val newFishTimers = (1..fishTimers.count { it == 0 }).map {
                8
            }
            fishTimers = fishTimers
                .map {
                    when {
                        it > 0 -> {
                            it - 1
                        }
                        it == 0 -> {
                            6
                        }
                        else -> {
                            throw IllegalStateException("Cannot have negative fish timer")
                        }
                    }
                }.plus(newFishTimers)
            println(fishTimers.count { it == 6 })
        }
        return (fishTimers.count()).toLong()
    }


}


class Day6B : Day {

    // From answer on https://github.com/jntakpe/aoc2021

    override fun run(): Long {
        val numberOfDays = 256
        var fishTimers = formatInput().groupBy { it }.mapValues { it.value.count().toLong() }.toMutableMap()
        for (i in (1..numberOfDays)) {
            val newFish = fishTimers[0] ?: 0
            fishTimers = fishTimers.filter { it.key != 0 }.mapKeys { it.key - 1 }.toMutableMap()
            if (newFish > 0) {
                println(newFish)
                fishTimers[6] = newFish + (fishTimers[6] ?: 0)
                fishTimers[8] = newFish
            }
            println(fishTimers[6])
        }
        return fishTimers.map { it.value }.sum()
    }


}

private fun formatInput() = Reader.readInputs("Day6.txt").first().split(",").map { it.toInt() }

