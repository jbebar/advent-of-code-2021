package org.jbebar.aoc21.day4

import org.jbebar.aoc21.day4.BingoBoard
import org.jbebar.aoc21.day4.BingoBoard.Square
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class BingoBoardTest {

    @Test
    fun `should initialize bingo board according to draw `() {
        val bingoBoard = BingoBoard(listOf(Square(23U, 0U, 0U, isMarked = false),
                                           Square(20U, 0U, 1U, isMarked = false),
                                           Square(3U, 1U, 0U, isMarked = false),
                                           Square(0U, 1U, 1U, isMarked = false)))

        Assertions.assertEquals(0, bingoBoard.markedSquares().size)
        Assertions.assertEquals(46, bingoBoard.unMarkedSum())
    }

    @Test
    fun `should mark square according to draw`() {
        val bingoBoard = BingoBoard(listOf(Square(23U, 0U, 0U, isMarked = false),
                                           Square(23U, 0U, 1U, isMarked = false),
                                           Square(5U, 1U, 0U, isMarked = false),
                                           Square(8U, 1U, 1U, isMarked = false)
        ))

        val newBingoBoard = bingoBoard.marksMatchingSquares(23U)

        Assertions.assertEquals(2, newBingoBoard.markedSquares().size)
        Assertions.assertEquals(13, newBingoBoard.unMarkedSum())
    }

    @Test
    fun `should return win to true when one row in marked`() {
        val bingoBoard = BingoBoard(listOf(
                Square(1U, 0U, 0U, isMarked = false),
                Square(23U, 0U, 1U, isMarked = false),
                Square(0U, 1U, 0U, isMarked = true),
                Square(10U, 1U, 1U, isMarked = true)
        ))

        Assertions.assertEquals(true, bingoBoard.wins())
    }

    @Test
    fun `should return win to true when one column in marked`() {
        val bingoBoard = BingoBoard(listOf(Square(1U, 0U, 0U, isMarked = true),
                                           Square(23U, 0U, 1U, isMarked = false),
                                           Square(0U, 1U, 0U, isMarked = true),
                                           Square(10U, 1U, 1U, isMarked = false)
        ))

        Assertions.assertEquals(true, bingoBoard.wins())
    }

    @Test
    fun `should return win to false when all column are not completely marked`() {
        val bingoBoard = BingoBoard(listOf(
                Square(1U, 0U, 0U, isMarked = true),
                Square(23U, 1U, 0U, isMarked = false),
                Square(8U, 0U, 1U, isMarked = false),
                Square(8U, 1U, 1U, isMarked = false),
        ))

        Assertions.assertEquals(false, bingoBoard.wins())
    }


    @Test
    fun `should return win to false when all rows are not completely marked`() {
        val bingoBoard = BingoBoard(listOf(
                Square(1U, 0U, 0U, isMarked = true),
                Square(23U, 1U, 0U, isMarked = false),
                Square(8U, 0U, 1U, isMarked = false),
                Square(8U, 1U, 1U, isMarked = false),
        ))

        Assertions.assertEquals(false, bingoBoard.wins())
    }

}