class Distances(val root: Cell) {
    fun forCell(cell: Cell): Int? {
        return cellDistances.get(cell)
    }

    fun set(cell: Cell, distance: Int) {
        cellDistances.put(cell, distance)
    }

    private var cellDistances: MutableMap<Cell, Int> = mutableMapOf(root to 0)
}
