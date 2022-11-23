class AldousBroder {
    fun on(grid: Grid): Grid {
        var cell = grid.randomCell()
        var unvisited = grid.size - 1

        while (unvisited > 0) {
            val neighbour = cell.neighbours().random()

            if (neighbour.links.isEmpty()) {
                cell.link(neighbour)
                unvisited--
            }

            cell = neighbour
        }
        return grid
    }
}