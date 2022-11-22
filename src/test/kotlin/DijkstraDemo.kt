import com.github.ajalt.mordant.rendering.AnsiLevel
import kotlin.test.Test
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.terminal.Terminal

class DijkstraDemo {
    @Test
    fun manualTest() {
        val tree = BinaryTree()
        val grid: DistanceGrid = tree.on(DistanceGrid(10, 10)) as DistanceGrid

        val start = grid.at(0, 0)
        val distances = start?.distances()

        grid.distances = distances!!
        val t = Terminal(AnsiLevel.TRUECOLOR)
        t.println(red(grid.toString()))

        grid.distances = distances.pathTo(grid.at(8, 6)!!)
        t.println(yellow(grid.toString()))

        val (newGoal, _) = distances.maxDistance()
        grid.distances = distances.pathTo(newGoal)
        t.println(blue(grid.toString()))

    }
}