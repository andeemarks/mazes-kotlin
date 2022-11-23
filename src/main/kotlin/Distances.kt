class Distances(val root: Cell) {
    private var cellDistances: MutableMap<Cell, Int> = mutableMapOf(root to 0)

    fun distanceFor(cell: Cell): Int? {
        return cellDistances[cell]
    }

    fun set(cell: Cell, distance: Int) {
        cellDistances[cell] = distance
    }

    fun pathTo(goal: Cell): Distances {
        var current = goal
        val breadcrumbs = Distances(root)
        breadcrumbs.set(goal, distanceFor(current)!!)

        do {
            current.links.forEach { linkedCell ->
                if (distanceFor(linkedCell)!! < distanceFor(current)!!) {
                    breadcrumbs.set(linkedCell, distanceFor(linkedCell)!!)
                    current = linkedCell
                }
            }
        } while (current != root)
        return breadcrumbs
    }

    fun length(): Int = cellDistances.size

    fun maxDistance(): Pair<Cell, Int> {
        val maxDistance = cellDistances.entries.maxByOrNull { it.value }!!

        return Pair(maxDistance.key, maxDistance.value)
    }

}
