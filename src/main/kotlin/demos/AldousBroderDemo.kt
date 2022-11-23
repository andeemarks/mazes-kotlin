package demos

import AldousBroder
import DistanceGrid
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class AldousBroderDemo(private val style: TextStyle = TextColors.white) {

    fun manualTest() {
        val tree = AldousBroder()
        val distanceGrid = DistanceGrid(20, 20)
        val grid = tree.on(distanceGrid) as DistanceGrid
        val middleCell = distanceGrid.at(10, 10)
        grid.distances = middleCell.distances()

        val t = Terminal(AnsiLevel.TRUECOLOR)

        t.println((style)(grid.toString()))
    }
}