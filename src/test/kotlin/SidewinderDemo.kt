import kotlin.test.Test

class SidewinderDemo {
    @Test
    fun manualTest() {
        val tree = Sidewinder()
        val grid = tree.on(Grid(10, 10))

        println(grid)
    }
}