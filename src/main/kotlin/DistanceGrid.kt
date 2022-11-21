class DistanceGrid(rows: Int, columns: Int) : Grid(rows, columns) {
    lateinit var distances: Distances

    fun distanceAsSingleChar(distance: Int): String = distance.toUInt().toString(35)

    override fun cellContentsFor(cell: Cell): String {
        if (distances.forCell(cell) != null) {
            return distanceAsSingleChar(distances.forCell(cell)!!)
        }

        return super.cellContentsFor(cell)
    }
}
