package demos

import AsciiMazePainter
import DistanceGrid
import algos.AldousBroder
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class AldousBroderDemo(private val style: TextStyle = TextColors.white) : Demoable {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    override fun run() {
        repeat(5) { i ->
            t.println("Aldous Broder iteration $i...")
            buildMaze()
        }
    }

    private fun buildMaze() {
        val tree = AldousBroder()
        val distanceGrid = DistanceGrid(10, 10)
        val grid = tree.on(distanceGrid) as DistanceGrid
        val middleCell = distanceGrid.at(5, 5)
        grid.distances = middleCell.distances()

        t.println((style)(AsciiMazePainter(grid, TextColors.blue).paint()))
    }
}