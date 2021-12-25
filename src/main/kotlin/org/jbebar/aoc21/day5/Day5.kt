package org.jbebar.aoc21.day5

import Day
import Reader.readInputLines

data class Point(val y: Int, val x: Int)

class Day5A : Day {

    override fun run(): Long {
        return readLines().map {
            it.first().toPoint() to it.last().toPoint()
        }.filter { (start, end) ->
            start.x == end.x || start.y == end.y
        }.flatMap { (start, end) ->
            toSegment(start, end)
        }.fold(mutableMapOf<Point, Int>()) { acc, point ->
            acc.apply {
                if (acc[point] != null) {
                    acc[point] = acc[point]!! + 1
                } else {
                    acc[point] = 1
                }
            }
        }.count {
            it.value >= 2
        }.toLong()
    }

}


class Day5B : Day {

    override fun run(): Long {
        val inputLines = readLines()
        return inputLines.map {
            it.first().toPoint() to it.last().toPoint()
        }.map { (start, end) ->
            toSegment(start, end)
        }.flatten().fold(mutableMapOf<Point, Int>()) { acc, point ->
            acc.apply {
                if (acc.contains(point)) {
                    acc[point] = acc[point]!! + 1
                } else {
                    acc[point] = 1
                }
            }
        }.count {
            it.value >= 2
        }.toLong()
    }

}

fun toSegment(startPoint: Point, endPoint: Point): List<Point> {
    val horizontalPart = toCoordinateSuite(startPoint.y, endPoint.y)
    val verticalPart = toCoordinateSuite(startPoint.x, endPoint.x)
    return if (horizontalPart.toList().size == verticalPart.toList().size) {
        horizontalPart.zip(verticalPart).map { (y, x) ->
            Point(y, x)
        }
    } else if (verticalPart.toList().isEmpty()) {
        horizontalPart.map { Point(y = it, x = startPoint.x) }
    } else if (horizontalPart.toList().isEmpty()) {
        verticalPart.map { Point(y = startPoint.y, x = it) }
    } else {
        emptyList()
    }
}

private fun toCoordinateSuite(start: Int, end: Int) =
    if (start < end) {
        (start..end)
    } else if (start == end) {
        emptyList()
    } else {
        (end..start).reversed()
    }

private fun String.toPoint() = split(",").let { coordinates -> Point(y = coordinates[0].toInt(), x = coordinates[1].toInt()) }

private fun readLines() = readInputLines("Day5.txt").map {
    it.split(" -> ")
}