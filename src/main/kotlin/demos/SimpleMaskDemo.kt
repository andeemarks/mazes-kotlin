package demos

import Mask
import MaskedGrid
import algos.RecursiveBacktracker
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.terminal.Terminal

class SimpleMaskDemo : Demoable {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    override fun run() {
        val mask = Mask(5, 5)
        mask.set(0, 0, false)
        mask.set(2, 2, false)
        mask.set(4, 4, false)

        val grid = MaskedGrid(mask)
        RecursiveBacktracker().on(grid)

        t.println(grid.toString())

    }
}

fun main() {
    SimpleMaskDemo().run()
}