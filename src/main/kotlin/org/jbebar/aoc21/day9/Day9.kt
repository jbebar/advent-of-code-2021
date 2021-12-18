@file:Suppress("unused")

import kotlin.math.max

class Day9A : Day {

    override fun run(): Long {
        return this::class.java.getResourceAsStream("Day9.txt")!!.reader().readText().countRiskLevel().toLong()
    }

}

class Day9B : Day {

    override fun run(): Long {
        TODO()
    }

}

fun String.read(): Map<Pair<Int, Int>, Int> {
    val heatMap = mutableMapOf<Pair<Int, Int>, Int>()
    this.lines().filter {
        it.isNotBlank()
    }.map {
        it.replace(" ", "")
    }.forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            heatMap[(y to x)] = c.digitToInt()
        }
    }
    return heatMap
}

fun String.countRiskLevel(): Int {
    val map = this.read()
    return map.entries.filter {
        it.isLowPoint(map)
    }.sumOf {
        it.value + 1
    }
}

fun String.countBasins(): Int {
    val map = this.read()
    val biggerBasins = map.entries.filter {
        it.isLowPoint(map)
    }.fold(listOf<Set<Pair<Pair<Int, Int>, Int>>>()) { basins, lowPoint ->
        val newBasinPoints = countNewBasinPoints(lowPoint.toPair(), map, setOf(lowPoint.toPair())).filter { point ->
            !basins.flatMap { it.toList() }.map { it.first }.toSet().contains(point.first)
        }.toSet()
        basins + listOf(newBasinPoints)
    }.map {
        it.size
    }.sorted().takeLast(3)

    return biggerBasins.getOrElse(0) {
        1
    } * biggerBasins.getOrElse(1) {
        1
    } * biggerBasins.getOrElse(2) {
        1
    }
}

private fun countNewBasinPoints(
        origin: Pair<Pair<Int, Int>, Int>,
        map: Map<Pair<Int, Int>, Int>,
        currentBasinPoints: Set<Pair<Pair<Int, Int>, Int>>,
): Set<Pair<Pair<Int, Int>, Int>> {
    val originCoordinates = origin.first
    val originHeight = origin.second

    val newBasinsPoints = mutableListOf<Pair<Pair<Int, Int>, Int>>()
    for (y in max(originCoordinates.first - 1, 0)..max(originCoordinates.first + 1, 0)) {
        for (x in max(originCoordinates.second - 1, 0)..max(originCoordinates.second + 1, 0)) {
            val pointHeight = map[y to x]
            if (pointHeight != null && originCoordinates != (y to x) && pointHeight != 9 && pointHeight > originHeight) {
                newBasinsPoints.add((y to x) to pointHeight)
            }
        }
    }

    val allPoints = (currentBasinPoints + newBasinsPoints).map { it.first }
    val validNewBasinsPoints = newBasinsPoints.filter { point ->
        point.first.hasEnoughNeighbors(allPoints)
    }.map { it.first }

    return if (validNewBasinsPoints.size <= 1 || currentBasinPoints.map { it.first }.containsAll(newBasinsPoints.map { it.first })) {
        currentBasinPoints
    } else {
        newBasinsPoints.flatMap {
            countNewBasinPoints(it, map, currentBasinPoints + it)
        }.toSet()
    }
}

private fun Pair<Int, Int>.hasEnoughNeighbors(otherPoints: List<Pair<Int, Int>>): Boolean {
    val neighbors = mutableListOf<Pair<Int, Int>>()
    for (y in max(first - 1, 0)..max(second + 1, 0)) {
        for (x in max(second - 1, 0)..max(first + 1, 0)) {
            val currentPoint = y to x
            if (otherPoints.contains(currentPoint)) {
                neighbors.add(currentPoint)
            }
        }
    }
    return neighbors.size >= 2
}

private fun Map.Entry<Pair<Int, Int>, Int>.isLowPoint(map: Map<Pair<Int, Int>, Int>): Boolean {
    val y = key.first
    val x = key.second
    val value = map[(y to x)]
    checkNotNull(value)

    val isDiagonalLower = map[(y + 1 to x + 1)] strictlyAbove value && map[(y - 1 to x - 1)] strictlyAbove value
    val isVerticalLower = map[(y + 1 to x)] strictlyAbove value && map[(y - 1 to x)] strictlyAbove value
    val isHorizontalLower = map[(y to x + 1)] strictlyAbove value && map[(y to x - 1)] strictlyAbove value
    return isDiagonalLower && isVerticalLower && isHorizontalLower
}

private infix fun Int?.strictlyAbove(other: Int) = this == null || this > other