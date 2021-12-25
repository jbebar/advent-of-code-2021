package org.jbebar.aoc21.day3

import Day
import Reader.readInputLines

class Day3A : Day {

    override fun run(): Long {
        val bits = retrieveBits()
        var gammaBits = ""

        for (i in 0 until bits.first().length) {
            val (ones, zeros) = bits.map {
                it[i]
            }.partition {
                it == '1'
            }
            gammaBits += if (ones.size > zeros.size) {
                "1"
            } else {
                "0"
            }
        }

        val epsilon = toEpsilon(gammaBits)
        val gamma = Integer.parseInt(gammaBits, 2)

        return (gamma * epsilon).toLong()
    }

    private fun toEpsilon(gammaBits: String): Int {
        return gammaBits.map {
            when (it) {
                '0' -> '1'
                '1' -> '0'
                else -> throw IllegalStateException()
            }
        }.fold("") { acc, c -> "$acc$c" }.let {
            Integer.parseInt(it, 2)
        }
    }


}


class Day3B : Day {

    override fun run(): Long {
        val bits = retrieveBits()
        val oxygenGeneratorRating = searchNumber(0, bits, true).let {
            Integer.parseInt(it, 2)
        }
        val c02ScrubberRating = searchNumber(0, bits, false).let {
            Integer.parseInt(it, 2)
        }
        return (oxygenGeneratorRating * c02ScrubberRating).toLong()
    }

    private fun searchNumber(currentIndex: Int, bits: List<String>, isMostCommonBitCriteria: Boolean): String {
        val mostCommonBit = computeMostCommonBit(bits, currentIndex)
        val criteriaBit = when {
            isMostCommonBitCriteria -> mostCommonBit
            else -> revertBit(mostCommonBit)
        }
        val filteredNumbers = bits.filter { number ->
            criteriaBit == number[currentIndex]
        }
        return if (filteredNumbers.size == 1) {
            filteredNumbers.first()
        } else {
            searchNumber(currentIndex + 1, filteredNumbers, isMostCommonBitCriteria)
        }
    }

    private fun revertBit(it: Char) = when (it) {
        '0' -> '1'
        '1' -> '0'
        else -> throw IllegalStateException()
    }

}


private fun computeMostCommonBit(bits: List<String>, index: Int): Char {
    return bits.map {
        it[index]
    }.partition {
        it == '1'
    }.let { (ones, zeros) ->
        if (ones.size >= zeros.size) {
            '1'
        } else {
            '0'
        }
    }
}

private fun retrieveBits() = readInputLines("Day3.txt")