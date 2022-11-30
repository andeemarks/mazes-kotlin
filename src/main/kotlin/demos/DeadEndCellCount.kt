package demos

import Grid
import algos.*
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextAlign
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.Borders
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal

@Suppress("SameParameterValue")
class DeadEndCellCount : Demoable {
    private val counts = mutableMapOf<String, Int>()

    override fun run() {
        val tries = 100
        val gridSize = 20

        repeat(tries) {
            recordCountsFor(BinaryTree(), gridSize)
            recordCountsFor(Sidewinder(), gridSize)
            recordCountsFor(AldousBroder(), gridSize)
            recordCountsFor(Wilsons(), gridSize)
            recordCountsFor(HuntAndKill(), gridSize)
            recordCountsFor(RecursiveBacktracker(), gridSize)
        }

        val averages = countAverages(tries)

        showCountAverages(averages, gridSize)
    }

    private fun countAverages(tries: Int) = counts.map { (algo, count) -> Pair(algo, count / tries) }.toMap()

    private fun showCountAverages(averages: Map<String, Int>, size: Int) {
        val t = Terminal(AnsiLevel.TRUECOLOR)
        t.println(
            table {
                tableBorders = Borders.ALL
                align = TextAlign.RIGHT
                header {
                    rowFrom(averages.keys)
                    style(TextColors.magenta, bold = true)
                }
                body {
                    style(TextColors.yellow)
                    rowFrom(averages.values.map { average -> "%d/%d".format(average, size * size) })
                    rowFrom(averages.values.map { average -> "%.0f%%".format(deadEndPercentage(average, size)) })
                }
            }
        )
    }

    private fun recordCountsFor(algo: Algo, gridSize: Int) {
        val deadEndCells = algo.on(Grid(gridSize, gridSize)).deadEndCells().size
        val algoName = algo::class.simpleName!!
        counts[algoName] = counts[algoName]?.plus(deadEndCells) ?: deadEndCells
    }

    private fun deadEndPercentage(average: Int, size: Int) = average / (size * size.toFloat()) * 100

}