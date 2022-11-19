import kotlin.test.Test
import kotlin.test.assertEquals

class BinaryTreeTest {
    @Test
    fun manualTest() {
        val tree = BinaryTree()
        val grid = tree.on(Grid(4, 4))

        val display = grid.toString()
        val rows = display.split("\n").size

        assertEquals(4, rows)
    }
}