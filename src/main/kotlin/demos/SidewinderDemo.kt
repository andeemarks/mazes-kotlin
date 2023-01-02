package demos

import AsciiMazePainter
import Grid
import ImageMazePainter
import algos.Sidewinder
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class SidewinderDemo(private val style: TextStyle = TextColors.brightWhite) : Demoable {
    override fun run() {
        val tree = Sidewinder()
        val grid = tree.on(Grid(10, 10))

        val t = Terminal(AnsiLevel.TRUECOLOR)
        t.println((style)(AsciiMazePainter(grid).paint()))
        ImageMazePainter(grid).paint()
    }
}

fun main() {
    SidewinderDemo().run()
}