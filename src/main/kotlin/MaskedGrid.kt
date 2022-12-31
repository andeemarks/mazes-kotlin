class MaskedGrid(val mask: Mask) : Grid(mask.rows, mask.columns) {
    init {
        cells = MutableList(rows) { row -> MutableList(columns) { column -> Cell(row, column) } }
        for (row in 0 until mask.rows) {
            for (column in 0 until mask.columns) {
                cells[row][column] = initCell(row, column)
            }

        }
    }

    fun initCell(row: Int, column: Int): Cell {
        val isCellMasked = mask.at(row, column)
        return if (isCellMasked) {
            super.at(row, column)
        } else {
            Cell.empty()
        }
    }

    override val size: Int = mask.count()
    override fun randomCell(): Cell {
        val (row, column) = mask.randomBit()

        return initCell(row, column)
    }

}
