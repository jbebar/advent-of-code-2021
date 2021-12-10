import kotlin.math.absoluteValue

class Day7A : Day() {

    override fun run(): Long {
        return formatInputs().keys.map { position ->
            position to formatInputs().mapValues { (comparedPosition, c) -> c * (comparedPosition - position).absoluteValue }.values.sum()
        }.minByOrNull {
            it.second
        }!!.second.toLong()
    }

}

class Day7B : Day() {

    override fun run(): Long {
        val inputPositions = formatInputs()
        val maxPosition = inputPositions.maxOf { it.key }
        val minPosition = inputPositions.minOf { it.key }
        return (minPosition..maxPosition).map { position ->
            position to inputPositions.mapValues { (comparedPosition, crabCount) -> crabCount * computeConsumption((comparedPosition - position).absoluteValue) }.values.sum()
        }.minByOrNull {
            it.second
        }!!.second.toLong()
    }


}

fun computeConsumption(distance: Int): Int {
    return if (distance > 1) {
        distance + computeConsumption(distance - 1)
    } else {
        distance
    }
}

private fun formatInputs() = Reader.readInputs("Day7.txt").first().split(",").map { it.toInt() }.groupBy { it }.mapValues { it.value.count() }
