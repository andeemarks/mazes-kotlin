open class Cell(val row: Int, val column: Int) {
    var west: Cell? = null
    var east: Cell? = null
    var south: Cell? = null
    var north: Cell? = null
    val links: MutableList<Cell> = mutableListOf()

    fun link(cell: Cell) {
        this.links.add(cell)
        cell.links.add(this)
    }

    fun unlink(cell: Cell) {
        this.links.remove(cell)
        cell.links.remove(this)
    }

    fun isLinkedTo(cell: Cell?): Boolean = this.links.contains(cell)

    fun distances(): Distances {
        val distances = Distances(this)
        var frontier = mutableListOf(this)

        while (frontier.isNotEmpty()) {
            val newFrontier = mutableListOf<Cell>()

            frontier.forEach { cell ->
                cell.links.forEach { linkedCell ->
                    if (distances.distanceFor(linkedCell) == null) {
                        distances.set(linkedCell, distances.distanceFor(cell)!! + 1)
                        newFrontier.add(linkedCell)
                    }
                }
            }
            frontier = newFrontier
        }
        return distances
    }

    fun neighbours(): List<Cell> {
        val neighbours = mutableListOf<Cell>()
        north?.let { neighbours.add(it) }
        south?.let { neighbours.add(it) }
        east?.let { neighbours.add(it) }
        west?.let { neighbours.add(it) }

        return neighbours
    }

    override fun equals(other: Any?): Boolean {
        val otherCell = other as Cell

        return otherCell.row == row && otherCell.column == column
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + column

        return result
    }
}
