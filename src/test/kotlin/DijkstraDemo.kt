import kotlin.test.Test

class DijkstraDemo {
    @Test
    fun manualTest() {
        val tree = BinaryTree()
        val grid: DistanceGrid = tree.on(DistanceGrid(10, 10)) as DistanceGrid

        val start = grid.at(0, 0)
        val distances = start?.distances()

        grid.distances = distances!!
        println(grid)

        grid.distances = distances.pathTo(grid.at(8, 6)!!)
        println(grid)

        val (newGoal, _) = distances.maxDistance()
        grid.distances = distances.pathTo(newGoal)
        println(grid)

    }
}