package algos

import Cell
import Grid

class RecursiveBacktracker : Algo {
    override fun on(grid: Grid): Grid {
        val stack = Stack()
        val startCell = grid.randomCell()
        stack.push(startCell)

        while (stack.isNotEmpty()) {
            val currentCell = stack.last()
            val neighbours = currentCell.neighbours().filter { it.links.isEmpty() }

            if (neighbours.isEmpty()) {
                stack.pop()
            } else {
                val neighbour = neighbours.random()
                currentCell.link(neighbour)
                stack.push(neighbour)
            }
        }

        return grid
    }

}

private class Stack {
    private val stack = ArrayDeque<Cell>()

    fun push(cell: Cell) = stack.addLast(cell)
    fun pop(): Cell = stack.removeLast()
    fun last(): Cell = stack.last()
    fun isNotEmpty(): Boolean = stack.isNotEmpty()
}
