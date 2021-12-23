package org.jbebar.aoc21.day9

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test

internal class Day9ATest : WithAssertions {

    @Test
    internal fun `should read one input line`() {
        val inputLine = "219"
        assertThat(inputLine.read()).containsExactlyInAnyOrderEntriesOf(mapOf(Point(0, 0) to 2, Point(0, 1) to 1, Point(0, 2) to 9))
    }

    @Test
    internal fun `should read two input line`() {
        val inputLine = """
            219
            313
            001
        """

        assertThat(inputLine.read()).containsExactlyInAnyOrderEntriesOf(
                mapOf(
                        Point(0 , 0) to 2,
                        Point(0 , 1) to 1,
                        Point(0 , 2) to 9,
                        Point(1 , 0) to 3,
                        Point(1 , 1) to 1,
                        Point(1 , 2) to 3,
                        Point(2 , 0) to 0,
                        Point(2 , 1) to 0,
                        Point(2 , 2) to 1,
                )
        )
    }

    @Test
    internal fun `should find one low point at corner`() {
        val inputLine = """
            199
            329
            222
        """

        assertThat(inputLine.countRiskLevel()).isEqualTo(2)
    }

    @Test
    internal fun `should find one low point at center`() {
        val inputLine = """
            788
            718
            777
        """

        assertThat(inputLine.countRiskLevel()).isEqualTo(2)
    }

    @Test
    internal fun `should find zero low point`() {
        val inputLine = """
            399
            359
            222
        """

        assertThat(inputLine.countRiskLevel()).isEqualTo(0)
    }

}



