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
        return Cell(Random.nextInt(this.rows), Random.nextInt(this.columns))
    }

    private fun configureCells() {
        eachCell { cell -> configureCell(cell) }
    }

    fun eachCell(executor: (cell: Cell) -> Unit) {
        cells.forEach { row -> row.forEach { cell -> executor(cell) } }
    }

    private fun eachRow(executor: (row: List<Cell>) -> Unit) {
        cells.forEach { row -> executor(row) }
    }

    private fun configureCell(cell: Cell): Cell {
        cell.north = this.at(cell.row - 1, cell.column)
        cell.south = this.at(cell.row - 1, cell.column)
        cell.east = this.at(cell.row - 1, cell.column)
        cell.west = this.at(cell.row - 1, cell.column)

        return cell
    }

    val size: Int = this.rows * this.columns
    val cells: MutableList<MutableList<Cell>> =
        MutableList(rows) { row -> MutableList(columns) { column -> Cell(row, column) } }

    override fun toString(): String {
        var display = "+${"---+".repeat(columns)}\n"

        eachRow { row ->
            var top = "|"
            var bottom = "+"
            row.forEach { cell ->
//                println("|${cell}|")
//                println("|${cell.east}|")
                val body = "   "
                val eastBoundary = if (cell.isLinkedTo(cell.east)) " " else "|"
                top = top.plus(body).plus(eastBoundary)
                val southBoundary = if (cell.isLinkedTo(cell.south)) "   " else "---"
                val corner = "+"
                bottom = bottom.plus(southBoundary).plus(corner)
            }
            display = display.plus(top).plus("\n")
            display = display.plus(bottom).plus("\n")
        }

        return display.trim()
    }

    init {
        this.configureCells()
    }
}
