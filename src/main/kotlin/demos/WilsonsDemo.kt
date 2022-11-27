package demos

import DistanceGrid
import Grid
import algos.Wilsons
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal

class WilsonsDemo {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    fun run(): Grid {
        var grid: Grid? = null
        repeat(5) { i ->
            t.println("Wilson's iteration $i...")
            grid = buildMaze()
        }

        return grid!!
    }

    private fun buildMaze(): Grid {
        val tree = Wilsons()
        val distanceGrid = DistanceGrid(10, 10, TextColors.green)
        val grid = tree.on(distanceGrid) as DistanceGrid
        val middleCell = distanceGrid.at(5, 5)
        grid.distances = middleCell.distances()

        t.println(grid.toString())

        return grid
    }
}