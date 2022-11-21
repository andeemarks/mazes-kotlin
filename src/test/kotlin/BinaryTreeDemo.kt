import kotlin.test.Test

class BinaryTreeDemo {
    @Test
    fun manualTest() {
        val tree = BinaryTree()
        val grid = tree.on(Grid(10, 10))

        println(grid)
    }
}