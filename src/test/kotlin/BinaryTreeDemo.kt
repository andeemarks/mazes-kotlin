import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.test.Test

class BinaryTreeDemo {
    @Test
    fun manualTest() {
        val tree = BinaryTree()
        val grid = tree.on(Grid(10, 10))

        val t = Terminal()

        t.println((TextColors.black on TextColors.red)(grid.toString()))
    }
}