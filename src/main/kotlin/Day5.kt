import Reader.readInputs

data class Point(val y: Int, val x: Int)

object Day5A : Day() {

    override fun run(): Int {
        return readInputs("Day5.txt").map {
            it.split(" -> ")
        }.flatMap {
            val startPoint = it.first().toPoint()
            val endPoint = it.last().toPoint()
            toSegment(startPoint, endPoint)
        }.groupBy {
            it
        }.map {
            it.key to it.value.count()
        }.count {
            it.second >= 2
        }
    }

    private fun toSegment(startPoint: Point, endPoint: Point) = if (startPoint.x == endPoint.x) {
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

    private fun String.toPoint() = split(",").let { coordinates -> Point(y = coordinates[0].toInt(), x = coordinates[1].toInt()) }

}

object Day5B : Day() {

    override fun run(): Int {
        return 1
    }

}