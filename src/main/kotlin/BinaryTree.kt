class BinaryTree {
    fun on(grid: Grid): Grid {
        grid.eachCell(::initialiseCell)

        return grid
    }

    private fun initialiseCell(cell: Cell): Cell {
        val neighbours = mutableListOf<Cell>()
        if (cell.north != null) {
            neighbours.add(cell.north!!)
        }
        if (cell.east != null) {
            neighbours.add(cell.east!!)
        }

        if (neighbours.isNotEmpty()) {
            neighbours.shuffle()
            val randomNeighbour = neighbours.first()
            cell.link(randomNeighbour)
        }

        return cell
    }
}