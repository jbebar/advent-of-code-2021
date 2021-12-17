@file:Suppress("unused")

class Day9A : Day() {

    override fun run(): Long {
        return this::class.java.getResourceAsStream("Day9.txt")!!.reader().readText().countRiskLevel().toLong()
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