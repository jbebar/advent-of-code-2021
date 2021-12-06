import Reader.readInputs

data class Point(val y: Int, val x: Int)

class Day5A : Day() {

    override fun run(): Int {
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
        }
    }

}


class Day5B : Day() {

    override fun run(): Int {
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
        }
    }

}

fun toSegment(startPoint: Point, endPoint: Point): List<Point> {
    val horizontalPart = toCoordinateSuite(startPoint.y, endPoint.y)
    val verticalPart = toCoordinateSuite(startPoint.x, endPoint.x)
    return if (horizontalPart.size == verticalPart.size) {
        horizontalPart.zip(verticalPart).map { (y, x) ->
            Point(y, x)
        }
    } else if (verticalPart.isEmpty()) {
        horizontalPart.map { Point(y = it, x = startPoint.x) }
    } else if (horizontalPart.isEmpty()) {
        verticalPart.map { Point(y = startPoint.y, x = it) }
    } else {
        emptyList()
    }
}

private fun toCoordinateSuite(start: Int, end: Int) =
    if (start < end) {
        (start..end).toList()
    } else if (start == end) {
        emptyList()
    } else {
        (end..start).toList()
    }

private fun String.toPoint() = split(",").let { coordinates -> Point(y = coordinates[0].toInt(), x = coordinates[1].toInt()) }

private fun readLines() = readInputs("Day5.txt").map {
    it.split(" -> ")
}