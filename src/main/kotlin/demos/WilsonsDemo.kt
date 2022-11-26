package demos

import DistanceGrid
import algos.Wilsons
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal

class WilsonsDemo {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    fun run() {
        repeat(5) { i ->
            t.println("Wilson's iteration $i...")
            buildMaze()
        }
    }

    private fun buildMaze() {
        val tree = Wilsons()
        val distanceGrid = DistanceGrid(10, 10, TextColors.green)
        val grid = tree.on(distanceGrid) as DistanceGrid
        val middleCell = distanceGrid.at(5, 5)
        grid.distances = middleCell.distances()

        t.println(grid.toString())
    }
}