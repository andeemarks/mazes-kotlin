package demos

import DistanceGrid
import Grid
import algos.AldousBroder
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class AldousBroderDemo(private val style: TextStyle = TextColors.white) {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    fun run(): Grid {
        var grid: Grid? = null
        repeat(5) { i ->
            t.println("Aldous Broder iteration $i...")
            grid = buildMaze()
        }

        return grid!!
    }

    private fun buildMaze(): Grid {
        val tree = AldousBroder()
        val distanceGrid = DistanceGrid(10, 10, TextColors.blue)
        val grid = tree.on(distanceGrid) as DistanceGrid
        val middleCell = distanceGrid.at(5, 5)
        grid.distances = middleCell.distances()

        t.println((style)(grid.toString()))

        return grid
    }
}