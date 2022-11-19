import kotlin.random.Random

class Grid(val rows: Int, val columns: Int) {
    fun at(row: Int, column: Int): Cell? {
        if (row >= this.rows || column >= this.columns) {
            return null
        }

        if (row < 0 || column < 0) {
            return null
        }

        return cells[row][column]
    }

    fun randomCell(): Cell {
        return Cell(Random.nextInt(this.rows),Random.nextInt(this.columns))
    }

    fun configureCells() {
        cells.forEach { row -> row.forEach { cell -> configureCell(cell)} }
    }

    private fun configureCell(cell: Cell) {
         cell.north = this.at(cell.row - 1, cell.column)
         cell.south = this.at(cell.row -  1, cell.column)
         cell.east = this.at(cell.row - 1, cell.column)
         cell.west = this.at(cell.row - 1, cell.column)
    }

    val size: Int = this.rows * this.columns
    val cells: MutableList<MutableList<Cell>> = MutableList(rows) { row -> MutableList(columns) { column -> Cell(row, column) } }
}