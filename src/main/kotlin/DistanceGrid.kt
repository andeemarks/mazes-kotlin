class DistanceGrid(rows: Int, columns: Int) : Grid(rows, columns) {
    lateinit var distances: Distances

    override fun cellContentsFor(cell: Cell): String {
        if (distances.forCell(cell) != null) {
            return distances.forCell(cell)!!.toUInt().toString(35)
        }

        return super.cellContentsFor(cell)
    }
}
