package org.jbebar.aoc21.day9

import Day

class Day9A : Day {

    override fun run(): Long {
        return this::class.java.getResourceAsStream("Day9.txt")!!.reader().readText().countRiskLevel().toLong()
    }

}

class Day9B : Day {

    override fun run(): Long {
        return this::class.java.getResourceAsStream("Day9.txt")!!.reader().readText().countBasins().toLong()
    }

}

fun String.read(): Map<Point, Int> {
    val heatMap = mutableMapOf<Point, Int>()
    this.lines().filter {
        it.isNotBlank()
    }.map {
        it.replace(" ", "")
    }.forEachIndexed { y, s ->
        s.forEachIndexed { x, c ->
            heatMap[Point(y, x)] = c.digitToInt()
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
    val allPoints = this.read()
    val biggerBasins = allPoints.filter {
        it.isLowPoint(allPoints)
    }.map { (point, height) ->
        findBasinPoints(originPointToHeight = point to height, allPointsToHeight = allPoints, basinPoints = mutableMapOf())
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

data class Point(val y: Int, val x: Int) {

    fun directNeighbors(pointsToHeight: Map<Point, Int>): Map<Point, Int> =
        pointsToHeight.filter { it.key in setOf(Point(y + 1, x), Point(y - 1, x), Point(y, x - 1), Point(y, x + 1)) }

}

private fun findBasinPoints(
        originPointToHeight: Pair<Point, Int>,
        allPointsToHeight: Map<Point, Int>,
        basinPoints: MutableMap<Point, Int>,
): Map<Point, Int> {
    val originPoint = originPointToHeight.first
    val originHeight = originPointToHeight.second
    basinPoints[originPoint] = originHeight
    val newBasinPoints = originPoint.directNeighbors(allPointsToHeight).filter {
        it.value strictlyAbove originHeight && it.value < 9 && !basinPoints.containsKey(it.key)
    }
    if (newBasinPoints.isNotEmpty()) {
        newBasinPoints.forEach {
            basinPoints.plusAssign(findBasinPoints(it.toPair(), allPointsToHeight, basinPoints))
        }
    }
    return basinPoints
}

private fun Map.Entry<Point, Int>.isLowPoint(map: Map<Point, Int>): Boolean {
    return key.directNeighbors(map).map {
        it.value
    }.all {
        it strictlyAbove value
    }
}

private infix fun Int?.strictlyAbove(other: Int) = this == null || this > other