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
    return map.entries.sumOf {
        val y = it.key.first
        val x = it.key.second
        val value = map[(y to x)]

        checkNotNull(value)

        val isDiagonalLower = map[(y + 1 to x + 1)] strictlyAbove value && map[(y - 1 to x - 1)] strictlyAbove value
        val isVerticalLower = map[(y + 1 to x)] strictlyAbove value && map[(y - 1 to x)] strictlyAbove value
        val isHorizontalLower = map[(y to x + 1)] strictlyAbove value && map[(y to x - 1)] strictlyAbove value
        if (isDiagonalLower && isVerticalLower && isHorizontalLower) {
            value + 1
        } else {
            0
        }
    }
}

private infix fun Int?.strictlyAbove(other: Int) = this == null || this > other