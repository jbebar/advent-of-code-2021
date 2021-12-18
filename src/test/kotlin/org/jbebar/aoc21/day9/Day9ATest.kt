package org.jbebar.aoc21.day9

import countRiskLevel
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test
import read

internal class Day9ATest : WithAssertions {

    @Test
    internal fun `should read one input line`() {
        val inputLine = "219"

        assertThat(inputLine.read()).containsExactlyInAnyOrderEntriesOf(mapOf((0 to 0) to 2, (0 to 1) to 1, (0 to 2) to 9))
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
                        (0 to 0) to 2,
                        (0 to 1) to 1,
                        (0 to 2) to 9,
                        (1 to 0) to 3,
                        (1 to 1) to 1,
                        (1 to 2) to 3,
                        (2 to 0) to 0,
                        (2 to 1) to 0,
                        (2 to 2) to 1,
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
            299
            319
            222
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



