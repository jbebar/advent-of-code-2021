package org.jbebar.aoc21.day10

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day10ATest : WithAssertions {

    private val day10A = Day10A()

    @Test
    internal fun `retrieve no illegal character`() {
        assertThat(day10A.retrieveIllegalCharacter("[]")).isNull()
    }

    @Test
    internal fun `retrieve first illegal character`() {
        assertThat(day10A.retrieveIllegalCharacter("[)")).isEqualTo(')')
    }

}