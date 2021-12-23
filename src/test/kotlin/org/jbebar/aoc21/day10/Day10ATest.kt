package org.jbebar.aoc21.day10

import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class Day10ATest : WithAssertions {

    private val day10A = Day10A()

    @Test
    internal fun `retrieve no illegal character`() {
        assertThat(day10A.retrieveIllegalCharacter("[]")).isNull()
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "<)",
        "<}",
        "<]",
        "{>"
    ]
    )
    internal fun `retrieve first illegal character simple case`(inputChunk: String) {
        assertThat(day10A.retrieveIllegalCharacter(inputChunk)).isEqualTo(inputChunk[1])
    }

    @ParameterizedTest
    @CsvSource(
            "<<<<<<), )",
            "<<<<<)[], )",
            "<<<[]<<), )",
            "<<<[]<<[)], )",
            "<<<[]<<)[], )",
            "<<<[]<<{}), )",
            "<<<[]<<)[{}], )",
            "<<<[]<<[{}]), )",
            "<<<[]<<[{}]), )",
            "{([(<{}[<>[]}>{[]{[(<()>, }",
    )
    internal fun `retrieve first illegal character complete case`(inputChunk: String, illegalCharacter:String) {
        assertThat(day10A.retrieveIllegalCharacter(inputChunk)).isEqualTo(illegalCharacter[0])
    }

    @Test
    internal fun `should count syntax error score for }`() {
        val inputLines =
            """
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                {([(<{}[<>[]}>{[]{[(<()>
            """.trimIndent()

        assertThat(day10A.countIllegalScoreText(inputLines)).isEqualTo(1197)
    }

    @Test
    internal fun `should count syntax error score for )`() {
        val inputLines =
            """
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                [[<[([]))<([[{}[[()]]]
            """.trimIndent()

        assertThat(day10A.countIllegalScoreText(inputLines)).isEqualTo(3)
    }

    @Test
    internal fun `should count syntax error score for braket`() {
        val inputLines =
            """
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                [{[{({}]{}}([{[{{{}}([]
            """.trimIndent()

        assertThat(day10A.countIllegalScoreText(inputLines)).isEqualTo(57)
    }

    @Test
    internal fun `should count syntax error score for chevron`() {
        val inputLines =
            """
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                <{([([[(<>()){}]>(<<{{
            """.trimIndent()

        assertThat(day10A.countIllegalScoreText(inputLines)).isEqualTo(25137)
    }

    @Test
    internal fun `should count syntax for complete case`() {
        val inputLines =
            """
                [({(<(())[]>[[{[]{<()<>>
                [(()[<>])]({[<{<<[]>>(
                {([(<{}[<>[]}>{[]{[(<()>
                (((({<>}<{<{<>}{[]{[]{}
                [[<[([]))<([[{}[[()]]]
                [{[{({}]{}}([{[{{{}}([]
                {<[[]]>}<{[{[{[]{()[[[]
                [<(<(<(<{}))><([]([]()
                <{([([[(<>()){}]>(<<{{
                <{([{{}}[<[[[<>{}]]]>[]]
            """.trimIndent()

        assertThat(day10A.countIllegalScoreText(inputLines)).isEqualTo(26397)
    }

}