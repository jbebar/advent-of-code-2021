import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class Day8Test : WithAssertions {

    private val allDigitsClassic = listOf("abcefg", "cf", "acdeg", "acdfg", "bdcf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg")
    private val classicOne = setOf('c', 'f')
    private val classicTwo = setOf('a', 'c', 'd', 'e', 'g')
    private val classicThree = setOf('a', 'c', 'd', 'f', 'g')
    private val classicFour = setOf('b', 'c', 'd', 'f')
    private val classicFive = setOf('a', 'b', 'd', 'f', 'g')
    private val classicSix = setOf('a', 'b', 'd', 'e', 'f', 'g')
    private val classicSeven = setOf('a', 'c', 'f')
    private val classicEight = setOf('a', 'b', 'c', 'd', 'e', 'f', 'g')
    private val classicNine = setOf('a', 'b', 'c', 'd', 'f', 'g')

    @Test
    fun `should find zero`() {
        assertThat(findZero(classicEight, classicNine, classicSix, allDigitsClassic)).containsExactlyInAnyOrder(*setOf('a', 'b', 'c', 'e', 'f', 'g').toTypedArray())
    }

    @Test
    fun `should find one`() {
        assertThat(findOne(allDigitsClassic)).containsExactlyInAnyOrder(*classicOne.toTypedArray())
    }

    @Test
    fun `should find two`() {
        assertThat(findTwo(five = classicFive, allDigits = allDigitsClassic)).containsExactlyInAnyOrder(*setOf('a', 'c', 'd', 'e', 'g').toTypedArray())
    }

    @Test
    fun `should find three`() {
        assertThat(findThree(five = classicFive, allDigits = allDigitsClassic)).containsExactlyInAnyOrder(*setOf('a', 'c', 'd', 'f', 'g').toTypedArray())
    }

    @Test
    fun `should find four`() {
        assertThat(findFour(allDigitsClassic)).containsExactlyInAnyOrder(*classicFour.toTypedArray())
    }

    @Test
    fun `should find five`() {
        assertThat(findFive(classicSix, allDigitsClassic)).containsExactlyInAnyOrder(*classicFive.toTypedArray())
    }

    @Test
    fun `should find six`() {
        assertTrue(findSix(classicSeven, allDigitsClassic).containsAll(classicSix))
    }

    @Test
    fun `should find seven`() {
        assertThat(findSeven(allDigitsClassic)).containsExactlyInAnyOrder(*classicSeven.toTypedArray())
    }

    @Test
    fun `should find eight`() {
        assertThat(findEight(allDigitsClassic)).containsExactlyInAnyOrder(*classicEight.toTypedArray())
    }

    @Test
    fun `should find nine`() {
        val actualNine = findNine(classicFive, classicSeven)

        assertThat(actualNine).containsExactlyInAnyOrder(*classicNine.toTypedArray())
    }

    @Test
    fun `should count one line total`() {
        val inputLine = listOf(classicFive, classicTwo, classicThree)
        val actualThree = digitInputToInt(allDigits = allDigitsClassic, inputDigits = inputLine)

        assertThat(actualThree).isEqualTo(523)
    }


    @Test
    fun `should count one line equals with seven eight and nine`() {
        val inputLine = listOf(classicSeven, classicEight, classicNine)
        val actualThree = digitInputToInt(allDigits = allDigitsClassic, inputDigits = inputLine)

        assertThat(actualThree).isEqualTo(789)
    }


    @Test
    fun `should count one line equals with seven eight and nine and ignore empty set`() {
        val inputLine = listOf(classicSeven, classicEight, classicNine, emptySet())
        val actualThree = digitInputToInt(allDigits = allDigitsClassic, inputDigits = inputLine)

        assertThat(actualThree).isEqualTo(789)
    }

}