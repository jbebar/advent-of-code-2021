package org.jbebar.aoc21.day9

import countBasins
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class Day9BTest : WithAssertions {

    @Test
    internal fun `should count basins size simple case`() {
        val inputMap =
            """
              7775
              8175
              7775
              5555
              5555
            """

        assertThat(inputMap.countBasins()).isEqualTo(9)
    }

    @Test
    internal fun `should count basins size  and ignore 9 values`() {
        val inputMap =
            """
              7998
              8195
              7775
              5555
              5555
            """

        assertThat(inputMap.countBasins()).isEqualTo(6)
    }

    @Test
    internal fun `should count basins size for river pattern`() {
        val inputMap =
            """
              9999
              9199
              9299
              9399
              9499
            """

        assertThat(inputMap.countBasins()).isEqualTo(1)
    }

    @Test
    internal fun `should count basins size for two points`() {
        val inputMap =
            """
              9999
              9199
              9299
              9922
              9921
            """

        assertThat(inputMap.countBasins()).isEqualTo(4)
    }

    @Disabled("WIP day 9B")
    @Test
    internal fun `should count basins size for multiple basins`() {
        val inputMap =
            """
              2199943210
              3987894921
              9856789892
              8767896789
              9899965678
            """

        assertThat(inputMap.countBasins()).isEqualTo(1134)
    }
}