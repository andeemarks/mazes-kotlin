package demos

import Grid
import MazePainter
import algos.RecursiveBacktracker
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class KillingCellsDemo(private val style: TextStyle = TextColors.brightWhite) : Demoable {

    override fun run() {
        val grid = Grid(5, 5)
        grid.at(0, 0).east!!.west = null
        grid.at(0, 0).south!!.north = null
        grid.at(4, 4).west!!.east = null
        grid.at(4, 4).north!!.south = null
        RecursiveBacktracker().on(grid, grid.at(1, 1))

        val t = Terminal(AnsiLevel.TRUECOLOR)

        t.println((style)(MazePainter().paint(grid)))
    }
}

fun main() {
    KillingCellsDemo().run()
}