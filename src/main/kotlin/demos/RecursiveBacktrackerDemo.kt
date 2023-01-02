package demos

import AsciiMazePainter
import DistanceGrid
import algos.RecursiveBacktracker
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class RecursiveBacktrackerDemo(private val style: TextStyle = TextColors.brightWhite) : Demoable {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    override fun run() {
        val tree = RecursiveBacktracker()
        val grid: DistanceGrid = tree.on(DistanceGrid(20, 20)) as DistanceGrid

        val start = grid.at(0, 0)
        val distances = start.distances()

        grid.distances = distances
        t.println((style)(AsciiMazePainter(grid, TextColors.magenta).paint()))

    }

}