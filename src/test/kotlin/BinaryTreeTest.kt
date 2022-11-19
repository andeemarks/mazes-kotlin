import kotlin.test.Test

class BinaryTreeTest {
    @Test
    fun manualTest() {
        val tree = BinaryTree()
        val grid = tree.on(Grid(4, 4))

        val display = grid.toString()

        println(display)
    }
}