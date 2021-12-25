package org.jbebar.aoc21.day8

import Day
import Reader

class Day8A : Day {

    override fun run(): Long {
        return Reader.readInputLines("Day8.txt").map {
            it.split("|")[1]
        }.map {
            it.split(" ")
        }.flatMap {
            it.map {
                it.length
            }
        }.groupBy {
            it
        }.mapValues {
            it.value.count()
        }.filter {
            it.key in setOf(2, 3, 4, 7)
        }.values.sum().toLong()
    }

}

class Day8B : Day {

    override fun run(): Long {
        return Reader.readInputLines("Day8.txt").map {
            it.split("|")
        }.map {
            it[0].split(" ") to it.toDigits()
        }.sumOf { (allDigits, inputDigits) ->
            digitInputToInt(allDigits, inputDigits)
        }.toLong()
    }

    private fun List<String>.toDigits() = this[1].split(" ").map { it.toDigit() }

}


fun digitInputToInt(allDigits: List<String>, inputDigits: List<Set<Char>>): Int {
    val digitsToSegments = mutableMapOf<Int, Set<Char>>()
    digitsToSegments[1] = findOne(allDigits)
    digitsToSegments[4] = findFour(allDigits)
    digitsToSegments[7] = findSeven(allDigits)
    digitsToSegments[8] = findEight(allDigits)
    digitsToSegments[6] = findSix(digitsToSegments[7]!!, allDigits)
    digitsToSegments[5] = findFive(digitsToSegments[6]!!, allDigits)
    digitsToSegments[2] = findTwo(digitsToSegments[5]!!, allDigits)
    digitsToSegments[3] = findThree(digitsToSegments[5]!!, allDigits)
    digitsToSegments[9] = findNine(digitsToSegments[5]!!, digitsToSegments[7]!!)
    digitsToSegments[0] = findZero(digitsToSegments[8]!!, digitsToSegments[9]!!, digitsToSegments[6]!!, allDigits)
    return inputDigits.filter {
        it.isNotEmpty()
    }.map { d ->
        digitsToSegments.entries.first { it.value.isStrictlyEqualTo(d) }
    }.map {
        it.key.toString()
    }.reduce { acc, s ->
        acc + s
    }.toInt()
}

private fun Set<Char>.isStrictlyEqualTo(otherDigit: Set<Char>) =
    this.containsAll(otherDigit) && otherDigit.containsAll(this)


fun findSix(seven: Set<Char>, allDigits: List<String>) =
    allDigits.filter {
        it.length == 6
    }.first { s ->
        s.filter { !seven.contains(it) }.length == 4
    }.toSet()


fun findFive(six: Set<Char>, allDigits: List<String>) =
    allDigits.map { s ->
        s.map { it }.toSet()
    }.filter {
        it.size == 5
    }.first {
        it.minus(six).isEmpty()
    }.toSet()

fun findSeven(allDigitsClassic: List<String>): Set<Char> {
    return allDigitsClassic.first {
        it.length == 3
    }.toDigit()
}

fun findFour(allDigitsClassic: List<String>): Set<Char> {
    return allDigitsClassic.first {
        it.length == 4
    }.toDigit()
}


fun findOne(allDigitsClassic: List<String>): Set<Char> {
    return allDigitsClassic.first {
        it.length == 2
    }.toDigit()
}


fun findEight(allDigitsClassic: List<String>): Set<Char> {
    return allDigitsClassic.first {
        it.length == 7
    }.toDigit()
}

private fun String.toDigit() = this.map { it }.toSet()

fun findNine(five: Set<Char>, seven: Set<Char>): Set<Char> {
    return five.union(seven)
}

fun findTwo(five: Set<Char>, allDigits: List<String>): Set<Char> {
    return allDigits.filter {
        it.length == 5
    }.map { s ->
        s.map { it }.toSet()
    }.first {
        it.minus(five).size == 2
    }
}

fun findThree(five: Set<Char>, allDigits: List<String>): Set<Char> {
    return allDigits.filter {
        it.length == 5
    }.map { s ->
        s.map { it }.toSet()
    }.first {
        it.minus(five).size == 1
    }
}

fun findZero(eight: Set<Char>, nine: Set<Char>, six: Set<Char>, allDigits: List<String>): Set<Char> {
    return allDigits.filter {
        eight.minus(it.toCharArray().toSet()).size == 1
    }.filter {
        !it.toCharArray().all { c -> c in nine }
    }.first {
        !it.toCharArray().all { c -> c in six }
    }.toSet()
}