package demos

import DistanceGrid
import Grid
import algos.HuntAndKill
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class HuntAndKillDemo(private val style: TextStyle = TextColors.brightWhite) {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    fun run(): Grid {
        t.println("Hunt and Kill...")
        val tree = HuntAndKill()
        val grid: DistanceGrid = tree.on(DistanceGrid(20, 20, TextColors.magenta)) as DistanceGrid

        val start = grid.at(0, 0)
        val distances = start.distances()

        grid.distances = distances
        t.println((style)(grid.toString()))

        return grid
    }

}