package algos

import Cell
import Grid

class Wilsons {
    fun on(grid: Grid): Grid {
        var unvisitedCells = mutableListOf<Cell>()

        grid.eachCell { cell -> unvisitedCells.add(cell) }

        val firstCell = unvisitedCells.random()
        unvisitedCells.remove(firstCell)

        while (unvisitedCells.isNotEmpty()) {
            val path = randomWalk(unvisitedCells)

            unvisitedCells = carvePassages(path, unvisitedCells)
        }
        return grid
    }

    private fun randomWalk(unvisitedCells: MutableList<Cell>): MutableList<Cell> {
        var cell = unvisitedCells.random()
        var path = mutableListOf(cell)

        while (unvisitedCells.contains(cell)) {
            cell = cell.neighbours().random()
            val position = path.indexOf(cell)
            if (position >= 0) {
                path = path.subList(0, position)
            } else {
                path.add(cell)
            }
        }
        return path
    }

    private fun carvePassages(path: MutableList<Cell>, unvisitedCells: MutableList<Cell>): MutableList<Cell> {
        for (i in 0..path.size - 2) {
            path[i].link(path[i + 1])
            unvisitedCells.remove(path[i])
        }

        return unvisitedCells
    }
}