package algos

import Cell
import Grid

class RecursiveBacktracker {
    fun on(grid: Grid): Grid {
        val stack = ArrayDeque<Cell>()
        val startCell = grid.randomCell()
        stack.addLast(startCell)

        while (stack.isNotEmpty()) {
            val currentCell = stack.last()
            val neighbours = currentCell.neighbours().filter { it.links.isEmpty() }

            if (neighbours.isEmpty()) {
                stack.removeLast()
            } else {
                val neighbour = neighbours.random()
                currentCell.link(neighbour)
                stack.addLast(neighbour)
            }
        }

        return grid
    }

}
