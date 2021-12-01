import Reader.readInputs

class Day1A {

    // For loops
    fun run() {
        val inputMeasurements = readInputs()

        var previousMeasurement: Int? = null
        var increasedMeasurementCounts = 0
        for (currentMeasurement in inputMeasurements.toList()) {
            if (previousMeasurement != null && previousMeasurement < currentMeasurement) {
                increasedMeasurementCounts++
            }
            previousMeasurement = currentMeasurement
        }
        println("Result $increasedMeasurementCounts")
    }

}

class Day1B {


    fun run() {

        // For loops way
        val inputMeasurements = readInputs()

        var previousMeasurement: Int? = null
        var increasedMeasurementCounts = 0

        val nextMeasurements = mutableListOf<Int>()
        for (i in 2 until inputMeasurements.size) {
            val windowSum = inputMeasurements[i - 2] + inputMeasurements[i - 1] + inputMeasurements[i]
            nextMeasurements.add(windowSum)
        }

        for (currentMeasurement in nextMeasurements) {
            if (previousMeasurement != null && previousMeasurement < currentMeasurement) {
                increasedMeasurementCounts++
            }
            previousMeasurement = currentMeasurement
        }

        println("Result $increasedMeasurementCounts")

        // Functional way
        val resultFunctionalWay = inputMeasurements.windowed(3, 1).map { it.sum() }.windowed(2).count { e -> e[0] < e[1] }
        println("Result $resultFunctionalWay")
    }

}

object Reader {

    fun readInputs() = this::class.java.getResourceAsStream("Day1.txt")!!.reader().readLines().map {
        it.toInt()
    }

}
