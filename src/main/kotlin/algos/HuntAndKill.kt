package algos

import Cell
import Grid

class HuntAndKill {
    fun on(grid: Grid): Grid {
        var currentCell: Cell? = grid.randomCell()

        while (currentCell != null) {
            val unvisitedNeighbours = currentCell.neighbours().filter { neighbour -> neighbour.links.isEmpty() }

            if (unvisitedNeighbours.isNotEmpty()) {
                val neighbour = unvisitedNeighbours.random()
                currentCell.link(neighbour)
                currentCell = neighbour
            } else {
                currentCell = null

                grid.eachCell { cell ->
                    val visitedNeighbours = cell.neighbours().filter { neighbour -> neighbour.links.isNotEmpty() }
                    if (cell.links.isEmpty() && visitedNeighbours.isNotEmpty()) {
                        currentCell = cell
                        val neighbour = visitedNeighbours.random()
                        currentCell!!.link(neighbour)
                    }
                }
            }
        }

        return grid
    }

}
