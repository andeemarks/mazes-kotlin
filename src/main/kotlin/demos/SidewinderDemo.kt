package demos

import Grid
import algos.Sidewinder
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class SidewinderDemo(private val style: TextStyle = TextColors.white) : Demoable {
    override fun run() {
        val tree = Sidewinder()
        val grid = tree.on(Grid(10, 10))

        val t = Terminal(AnsiLevel.TRUECOLOR)
        t.println((style)(grid.toString()))
    }
}