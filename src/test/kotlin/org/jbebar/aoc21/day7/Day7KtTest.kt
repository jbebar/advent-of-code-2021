package org.jbebar.aoc21.day7

import org.jbebar.aoc21.day7.computeConsumption
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day7KtTest {


    @ParameterizedTest
    @CsvSource(
            value = [
                "1, 1",
                "2, 3",
                "3, 6",
                "5, 15",
                "11, 66",
            ]
    )
    fun `should compute consumption for number of steps`(stepsCount: Int, expectedFuelConsumption: Int) {
        assertEquals(computeConsumption(stepsCount), expectedFuelConsumption)
    }
}