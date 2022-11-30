package algos

import Grid

class AldousBroder : Algo {
    override fun on(grid: Grid): Grid {
        var cell = grid.randomCell()
        var unvisitedCellCount = grid.size - 1

        while (unvisitedCellCount > 0) {
            val randomNeighbour = cell.neighbours().random()

            if (randomNeighbour.links.isEmpty()) {
                cell.link(randomNeighbour)
                unvisitedCellCount--
            }

            cell = randomNeighbour
        }
        return grid
    }
}