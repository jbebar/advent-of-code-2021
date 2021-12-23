package org.jbebar.aoc21.day10

import Day
import Reader.readInputText
import kotlin.math.abs

class Day10A : Day {

    override fun run() = countIllegalScoreText(readInputText("Day10.txt")).toLong()
    
    fun countIllegalScoreText(text: String): Int =
        text.lines().mapNotNull {
            retrieveIllegalCharacter(it)
        }.map {
            when (it) {
                '}' -> 1197
                ')' -> 3
                ']' -> 57
                '>' -> 25137
                else -> throw IllegalStateException()
            }
        }.sum()


    fun retrieveIllegalCharacter(line: String): Char? {
        val closingCharacterIndex = line.indexOfFirst {
            it in closingCharactersToOpening.keys
        }
        if (closingCharacterIndex == -1) {
            return null
        }
        val closingCharacter = line[closingCharacterIndex]
        val analysedChunk = line.substring(0, closingCharacterIndex)
        val openingCharacter = closingCharactersToOpening[closingCharacter]!!

        if (!analysedChunk.contains(openingCharacter)) {
            return line[closingCharacterIndex]
        }

        val openingCharacterIndex = analysedChunk.indexOfLast {
            it == openingCharacter
        }
        return if (abs(openingCharacterIndex - closingCharacterIndex) == 1) {
            val newAnalysedLine = line.filterIndexed { index, _ -> index != openingCharacterIndex && index != closingCharacterIndex }
            retrieveIllegalCharacter(newAnalysedLine)
        } else {
            line[closingCharacterIndex]
        }
    }

    private val closingCharactersToOpening = mapOf(')' to '(',
                                                   ']' to '[',
                                                   '}' to '{',
                                                   '>' to '<'
    )

}
