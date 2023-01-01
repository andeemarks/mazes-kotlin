package demos

import Grid
import MazePainter
import algos.BinaryTree
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class BinaryTreeDemo(private val style: TextStyle = TextColors.brightWhite) : Demoable {

    override fun run() {
        val tree = BinaryTree()
        val grid = tree.on(Grid(10, 10))

        val t = Terminal(AnsiLevel.TRUECOLOR)

        t.println((style)(MazePainter(grid).paint()))
    }
}

fun main() {
    BinaryTreeDemo().run()
}