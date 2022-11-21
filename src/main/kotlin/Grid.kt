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


    fun eachCell(executor: (cell: Cell) -> Unit) {
        cells.forEach { row -> row.forEach { cell -> executor(cell) } }
    }

    private fun eachRow(executor: (row: List<Cell>) -> Unit) {
        cells.forEach { row -> executor(row) }
    }

    private fun configureCells() {
        eachCell(::configureCell)
    }

    private fun configureCell(cell: Cell): Cell {
        cell.north = at(cell.row - 1, cell.column)
        cell.south = at(cell.row + 1, cell.column)
        cell.east = at(cell.row, cell.column + 1)
        cell.west = at(cell.row, cell.column - 1)

        return cell
    }

    val size: Int = this.rows * this.columns
    val cells: MutableList<MutableList<Cell>> =
        MutableList(rows) { row -> MutableList(columns) { column -> Cell(row, column) } }

    override fun toString(): String {
        var display = "+${"---+".repeat(columns)}\n"

        eachRow { row ->
            val (rowTop, rowBottom) = rowToString(row)
            display += "$rowTop\n$rowBottom\n"
        }

        return display.trim()
    }

    private fun rowToString(row: List<Cell>): Pair<String, String> {
        val rowCorner = "+"
        val cellBody = "   "
        var rowTop = "|"
        var rowBottom = "+"
        row.forEach { cell ->
            val cellEastBoundary = if (cell.isLinkedTo(cell.east)) " " else "|"
            rowTop += "$cellBody$cellEastBoundary"
            val cellSouthBoundary = if (cell.isLinkedTo(cell.south)) "   " else "---"
            rowBottom += "$cellSouthBoundary$rowCorner"
        }
        return Pair(rowTop, rowBottom)
    }

    init {
        this.configureCells()
    }
}
