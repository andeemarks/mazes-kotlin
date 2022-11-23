package demos

import DistanceGrid
import algos.BinaryTree
import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.ajalt.mordant.terminal.Terminal

class DijkstraDemo(private val style: TextStyle = TextColors.white) {
    fun manualTest() {
        val tree = BinaryTree()
        val grid: DistanceGrid = tree.on(DistanceGrid(10, 10)) as DistanceGrid

        val start = grid.at(0, 0)
        val distances = start.distances()

        grid.distances = distances
        val t = Terminal(AnsiLevel.TRUECOLOR)
        t.println("All cell distances from (0, 0)...")
        t.println((style)(grid.toString()))

        grid.distances = distances.pathTo(grid.at(8, 6))
        t.println("Shortest path from (0, 0) to (8, 6)...")
        t.println((style)(grid.toString()))

        val (newGoal, _) = distances.maxDistance()
        grid.distances = distances.pathTo(newGoal)
        t.println("Longest path from (0, 0)...")
        t.println((style)(grid.toString()))

    }
}