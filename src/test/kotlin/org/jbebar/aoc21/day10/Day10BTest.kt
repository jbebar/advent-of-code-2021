package org.jbebar.aoc21.day10

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test

internal class Day10BTest : WithAssertions {

    @Test
    internal fun `should complete line for first example`() {
        val day10B = Day10B()

        val completeLine = day10B.completeLine("[({(<(())[]>[[{[]{<()<>>")

        assertThat(completeLine).isEqualTo("}}]])})]")
    }

    @Test
    internal fun `should complete line for second example`() {
        val day10B = Day10B()

        val completeLine = day10B.completeLine("[(()[<>])]({[<{<<[]>>(")

        assertThat(completeLine).isEqualTo(")}>]})")
    }

    @Test
    internal fun `should return empty string for complete line`() {
        val day10B = Day10B()

        val completeLine = day10B.completeLine("[]")

        assertThat(completeLine).isEmpty()
    }

}