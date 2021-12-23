package org.jbebar.aoc21.day10

import Day

class Day10A : Day {

    override fun run(): Long {
        TODO()
    }

    val closingCharacters = listOf(')')

    fun retrieveIllegalCharacter(line: String): Char? {
        val closingCharacterIndex = line.indexOfFirst {
            it in closingCharacters
        }
        return if (closingCharacterIndex != -1 && !line.subSequence(0, closingCharacterIndex).contains('(')) {
            line[closingCharacterIndex]
        } else {
            null
        }
    }

}
