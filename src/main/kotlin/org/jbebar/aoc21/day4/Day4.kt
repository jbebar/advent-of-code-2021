package org.jbebar.aoc21.day4

import Day
import Reader.readInputLines

class BingoBoard(private val squares: List<Square>, val firstWinningDraw: UInt? = null) {

    init {
        check(squares.maxOf { it.row }.toInt() == squares.maxOf { it.column }.toInt())
        val size = squares.maxOf { it.row }.toInt() + 1
        check(squares.groupBy {
            it.row
        }.all {
            it.value.size == size
        })
        check(squares.groupBy {
            it.column
        }.all {
            it.value.size == size
        })
    }

    class Square(val value: UInt, val row: UInt, val column: UInt, val isMarked: Boolean = false) {
        fun mark() = Square(this.value, this.row, this.column, true)
    }

    fun marksMatchingSquares(newValue: UInt): BingoBoard {
        val newSquares = this.squares.map {
            if (it.value == newValue) {
                it.mark()
            } else {
                it
            }
        }
        val newBoard = BingoBoard(newSquares)
        return if (newBoard.wins()) {
            BingoBoard(newSquares, newValue)
        } else {
            newBoard
        }
    }

    fun markedSquares() = this.squares.filter { it.isMarked }

    fun wins(): Boolean {
        return (this.squares.groupBy {
            it.row
        }.any { rows ->
            rows.value.all { it.isMarked }
        }) || (this.squares.groupBy {
            it.column
        }.any { columns ->
            columns.value.all { it.isMarked }
        })
    }

    fun unMarkedSum() = squares.filter { !it.isMarked }.sumOf { it.value }.toInt()

}

class Day4A : Day {

    override fun run(): Long {
        val draws = readDraws()
        val bingoBoards = readBingoBoards(readInputLines("Day4.txt"))

        val result = draws.runningFold(bingoBoards) { acc, uInt ->
            acc.map { bingoBoard ->
                bingoBoard.marksMatchingSquares(uInt)
            }
        }.first {
            it.any { b -> b.wins() }
        }.first {
            it.wins()
        }.let {
            it.unMarkedSum() * it.firstWinningDraw!!.toInt()
        }
        return result.toLong()
    }

}

class Day4B : Day {

    override fun run(): Long {
        val draws = readDraws()

        val bingoBoards = readBingoBoards(readInputLines("Day4.txt"))

        val result = draws.runningFold(bingoBoards) { acc, uInt ->
            acc.map { bingoBoard ->
                bingoBoard.marksMatchingSquares(uInt)
            }.filter {
                !it.wins()
            }
        }.dropLastWhile {
            it.isEmpty()
        }.mapIndexed { index, boards ->
            draws[index] to boards
        }.last { (_, boards) ->
            boards.size == 1
        }.let { (lastWinningDraw, boards) ->
            lastWinningDraw to boards.first().marksMatchingSquares(lastWinningDraw)
        }.let { (lastWinningDraw, boards) ->
            lastWinningDraw * boards.unMarkedSum().toUInt()
        }
        return result.toLong()
    }

}


private fun readDraws() = readInputLines("Day4.txt").first().split(",").map { it.toUInt() }

private fun readBingoBoards(lines: List<String>): List<BingoBoard> {
    val boardSize = lines.drop(2).takeWhile { it.isNotBlank() }.size
    return lines.asSequence().drop(2).filter { it.isNotBlank() }.chunked(boardSize).map { board ->
        board.flatMapIndexed { x, l ->
            l.rowToSquares(x)
        }
    }.map { boardSquares ->
        BingoBoard(boardSquares)
    }.toList()
}

private fun String.rowToSquares(x: Int) = split(" ").filter { it.isNotBlank() }.mapIndexed { y, v ->
    BingoBoard.Square(v.toUInt(), x.toUInt(), y.toUInt())
}
