class MaskedGrid(val mask: Mask) : Grid(mask.rows, mask.columns) {
    override val size: Int = mask.count()
    override fun randomCell(): Cell {
        val (row, column) = mask.randomBit()

        return at(row, column)
    }
}
