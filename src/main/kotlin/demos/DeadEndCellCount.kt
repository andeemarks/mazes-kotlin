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
    override fun run() {
        val deadEndCellCount = mutableMapOf<String, Int>()
        val tries = 100
        val size = 20

        repeat(tries) {
            var deadEndCells = BinaryTree().on(Grid(size, size)).deadEndCells().size
            record(deadEndCellCount, deadEndCells, "binaryTree")
            deadEndCells = Sidewinder().on(Grid(size, size)).deadEndCells().size
            record(deadEndCellCount, deadEndCells, "sideWinder")
            deadEndCells = AldousBroder().on(Grid(size, size)).deadEndCells().size
            record(deadEndCellCount, deadEndCells, "aldousBroder")
            deadEndCells = Wilsons().on(Grid(size, size)).deadEndCells().size
            record(deadEndCellCount, deadEndCells, "wilsons")
            deadEndCells = HuntAndKill().on(Grid(size, size)).deadEndCells().size
            record(deadEndCellCount, deadEndCells, "huntAndKill")
            deadEndCells = RecursiveBacktracker().on(Grid(size, size)).deadEndCells().size
            record(deadEndCellCount, deadEndCells, "recursiveBacktracker")
        }

        val averages = deadEndCellCount.map { (algo, count) -> Pair(algo, count / tries) }.toMap()

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

    private fun deadEndPercentage(average: Int, size: Int) = average / (size * size.toFloat()) * 100

    private fun record(
        deadEndCellCount: MutableMap<String, Int>, deadEndCells: Int, algoName: String
    ) {
        deadEndCellCount[algoName] = deadEndCellCount[algoName]?.plus(deadEndCells) ?: deadEndCells
    }
}