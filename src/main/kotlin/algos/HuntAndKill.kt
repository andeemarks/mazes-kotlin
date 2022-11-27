package algos

import Cell
import Grid

class HuntAndKill {
    fun on(grid: Grid): Grid {
        var currentCell: Cell? = grid.randomCell()

        while (currentCell != null) {
            val unvisitedNeighbours = currentCell.neighbours().filter { neighbour -> neighbour.links.isEmpty() }

            currentCell = if (unvisitedNeighbours.isNotEmpty()) {
                val neighbour = unvisitedNeighbours.random()
                currentCell.link(neighbour)
                neighbour
            } else {
                hunt(grid)
            }
        }

        return grid
    }

    private fun hunt(grid: Grid): Cell? {
        var targetCell: Cell? = null

        grid.eachCell { cell ->
            val visitedNeighbours = cell.neighbours().filter { neighbour -> neighbour.links.isNotEmpty() }
            if (cell.links.isEmpty() && visitedNeighbours.isNotEmpty()) {
                targetCell = cell
                val neighbour = visitedNeighbours.random()
                targetCell!!.link(neighbour)
            }
        }
        return targetCell
    }

}
