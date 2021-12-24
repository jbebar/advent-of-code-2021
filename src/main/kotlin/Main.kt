import org.jbebar.aoc21.day1.Day1B
import org.jbebar.aoc21.day10.Day10A
import org.jbebar.aoc21.day10.Day10B
import org.jbebar.aoc21.day2.Day2A
import org.jbebar.aoc21.day2.Day2B
import org.jbebar.aoc21.day3.Day3A
import org.jbebar.aoc21.day3.Day3B
import org.jbebar.aoc21.day4.Day4A
import org.jbebar.aoc21.day4.Day4B
import org.jbebar.aoc21.day5.Day5A
import org.jbebar.aoc21.day5.Day5B
import org.jbebar.aoc21.day6.Day6A
import org.jbebar.aoc21.day6.Day6B
import org.jbebar.aoc21.day7.Day7A
import org.jbebar.aoc21.day7.Day7B
import org.jbebar.aoc21.day8.Day8A
import org.jbebar.aoc21.day8.Day8B
import org.jbebar.aoc21.day9.Day9A
import org.jbebar.aoc21.day9.Day9B

fun main(args: Array<String>) {
    val day = mapOf<String, Day>(
            Day1B().toRunEntry(),
            Day1B().toRunEntry(),
            Day2A().toRunEntry(),
            Day2B().toRunEntry(),
            Day3A().toRunEntry(),
            Day3B().toRunEntry(),
            Day4A().toRunEntry(),
            Day4B().toRunEntry(),
            Day5A().toRunEntry(),
            Day5B().toRunEntry(),
            Day6A().toRunEntry(),
            Day6B().toRunEntry(),
            Day7A().toRunEntry(),
            Day7B().toRunEntry(),
            Day8A().toRunEntry(),
            Day8B().toRunEntry(),
            Day9A().toRunEntry(),
            Day9B().toRunEntry(),
            Day10A().toRunEntry(),
            Day10B().toRunEntry(),
    )[args[0]]!!
    println("Result ${args[0]} : ${day.run()}")
}

private fun <T: Day> T.toRunEntry() = this::class.java.simpleName to this

interface Day {

    fun run(): Long

}
