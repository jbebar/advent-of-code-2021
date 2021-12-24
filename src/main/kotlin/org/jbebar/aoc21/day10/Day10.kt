package org.jbebar.aoc21.day10

import Day
import Reader.readInputText
import kotlin.math.abs

private val closingCharactersToOpening = mapOf(')' to '(',
                                               ']' to '[',
                                               '}' to '{',
                                               '>' to '<')

private val openingCharactersToClosing = mapOf('(' to ')',
                                               '[' to ']',
                                               '{' to '}',
                                               '<' to '>')


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

}

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

class Day10B : Day {

    override fun run(): Long {
        val inputText = readInputText("Day10.txt")
        return inputText.lines().filter {
            retrieveIllegalCharacter(it) == null
        }.map {
            completeLine(it)
        }.filter {
            it.isNotEmpty()
        }.map {
            it.map { c ->
                when (c) {
                    ')' -> 1L
                    ']' -> 2L
                    '}' -> 3L
                    '>' -> 4L
                    else -> throw IllegalStateException()
                }
            }.fold(0) { score: Long, characterScore: Long ->
                score * 5 + characterScore
            }
        }.sorted().let {
            it[(it.size - 1) / 2]
        }
    }

    fun completeLine(line: String): String {
        val cleanedLine = line.replace("()", "").replace("{}", "").replace("[]", "").replace("<>", "")
        return if (cleanedLine.windowed(2).any { it in listOf("()", "{}", "[]", "<>") }) {
            completeLine(cleanedLine)
        } else {
            cleanedLine.mapNotNull {
                openingCharactersToClosing[it]
            }.reversed().joinToString("")
        }

    }
}


