import Reader.readInputs

data class Point(val y: Int, val x: Int)

class Day5A : Day() {

    override fun run(): Int {
        return readLines().map {
            it.first().toPoint() to it.last().toPoint()
        }.filter { (start, end) ->
            start.x == end.x || start.y == end.y
        }.flatMap { (start, end) ->
            toHorizontalOrVerticalSegment(start, end)
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

    private fun toHorizontalOrVerticalSegment(startPoint: Point, endPoint: Point) =
        if (startPoint.x == endPoint.x) {
            if (startPoint.y > endPoint.y) {
                (endPoint.y..startPoint.y).map { y ->
                    Point(y, startPoint.x)
                }
            } else {
                (startPoint.y..endPoint.y).map { y ->
                    Point(y, startPoint.x)
                }
            }
        } else {
            if (startPoint.x > endPoint.x) {
                (endPoint.x..startPoint.x).map { x ->
                    Point(startPoint.y, x)
                }
            } else {
                (startPoint.x..endPoint.x).map { x ->
                    Point(startPoint.y, x)
                }
            }
        }


}

object Day5B : Day() {

    override fun run(): Int {
        val inputLines = readLines()
        return inputLines.map {
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

    private fun toSegment(startPoint: Point, endPoint: Point): List<Point> {
        return if (startPoint.x == endPoint.x) {
            if (startPoint.y > endPoint.y) {
                (endPoint.y..startPoint.y).map { y ->
                    Point(y, startPoint.x)
                }
            } else {
                (startPoint.y..endPoint.y).map { y ->
                    Point(y, startPoint.x)
                }
            }
        } else {
            if (startPoint.x > endPoint.x) {
                (endPoint.x..startPoint.x).map { x ->
                    Point(startPoint.y, x)
                }
            } else {
                (startPoint.x..endPoint.x).map { x ->
                    Point(startPoint.y, x)
                }
            }
        }
    }

}

private fun String.toPoint() = split(",").let { coordinates -> Point(y = coordinates[0].toInt(), x = coordinates[1].toInt()) }

private fun readLines() = readInputs("Day5.txt").map {
    it.split(" -> ")
}