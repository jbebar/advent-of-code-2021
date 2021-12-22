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
              777
              817
              777
            """

        assertThat(inputMap.countBasins()).isEqualTo(5)
    }

    @Test
    internal fun `should count basins size  and ignore 9 values`() {
        val inputMap =
            """
              999
              919
              999
            """

        assertThat(inputMap.countBasins()).isEqualTo(1)
    }

    @Test
    internal fun `should count basins size for line of points`() {
        val inputMap =
            """
              9999
              9199
              9299
              9399
              9499
            """

        assertThat(inputMap.countBasins()).isEqualTo(4)
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

        assertThat(inputMap.countBasins()).isEqualTo(6)
    }

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