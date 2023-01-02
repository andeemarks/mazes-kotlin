class MaskedGrid(val mask: Mask) : Grid(mask.rows, mask.columns) {
    override val size: Int = mask.count()

    init {
        cells = MutableList(rows) { row -> MutableList(columns) { column -> initCell(row, column) } }
    }

    override fun randomCell(): Cell {
        val (row, column) = mask.randomBit()

        return initCell(row, column)
    }

    fun initCell(row: Int, column: Int): Cell {
        val isCellMasked = mask.at(row, column)
        return if (isCellMasked) {
            super.at(row, column)
        } else {
            NullCell(row, column)
        }
    }

}
