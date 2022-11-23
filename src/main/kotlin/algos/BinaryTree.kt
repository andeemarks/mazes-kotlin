package algos

import Cell
import Grid

class BinaryTree {
    fun on(grid: Grid): Grid {
        grid.eachCell(::initialiseCell)

        return grid
    }

    private fun initialiseCell(cell: Cell): Cell {
        val neighbours = mutableListOf<Cell>()
        cell.north?.let {neighbours.add(cell.north!!)}
        cell.east?.let {neighbours.add(cell.east!!)}

        if (neighbours.isNotEmpty()) {
            cell.link(neighbours.random())
        }

        return cell
    }
}