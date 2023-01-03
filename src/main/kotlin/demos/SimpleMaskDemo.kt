package demos

import AsciiMazePainter
import ImageMazePainter
import Mask
import MaskedGrid
import algos.RecursiveBacktracker
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import java.io.File

class SimpleMaskDemo : Demoable {

    private val t = Terminal(AnsiLevel.TRUECOLOR)

    override fun run() {
        codeDescribedMask()

        t.println()

        dslDescribedMask()
    }

    private fun dslDescribedMask() {
        val mask = Mask.from(File("ascii-mask.txt").readLines())
        val grid = RecursiveBacktracker().on(MaskedGrid(mask))
        ImageMazePainter(grid).paint("ascii-mask.svg")
    }

    private fun codeDescribedMask() {
        val mask = Mask(5, 5)
        mask.set(0, 0, false)
        mask.set(2, 2, false)
        mask.set(4, 4, false)

        val grid = RecursiveBacktracker().on(MaskedGrid(mask))
        t.println((TextColors.black)(AsciiMazePainter(grid).paint()))
    }
}

fun main() {
    SimpleMaskDemo().run()
}