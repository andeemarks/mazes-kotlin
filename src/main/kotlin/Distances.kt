class Distances(val root: Cell) {
    fun forCell(cell: Cell): Int? {
        return cellDistances[cell]
    }

    fun set(cell: Cell, distance: Int) {
        cellDistances[cell] = distance
    }

    fun pathTo(goal: Cell): Distances {
        var current = goal
        val breadcrumbs = Distances(root)
        breadcrumbs.set(goal, forCell(current)!!)

        do {
            current.links.forEach { linkedCell ->
                if (forCell(linkedCell)!! < forCell(current)!!) {
                    breadcrumbs.set(linkedCell, forCell(linkedCell)!!)
                    current = linkedCell
                }
            }
        } while (current != root)
        return breadcrumbs
    }

    fun length(): Int = cellDistances.size
    fun maxDistance(): Pair<Cell, Int> {
        var maxDistance = 0
        var maxCell = root

        cellDistances.forEach { (cell, distance) ->
            if (distance > maxDistance) {
                maxCell = cell
                maxDistance = distance
            }
        }

        return Pair(maxCell, maxDistance)
    }

    private var cellDistances: MutableMap<Cell, Int> = mutableMapOf(root to 0)
}
