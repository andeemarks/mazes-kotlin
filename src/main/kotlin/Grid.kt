import com.github.ajalt.mordant.rendering.TextStyle
import kotlin.random.Random

private const val ROW_CORNER = "┼"

private const val ROW_SIDE = "│"

private const val ROW_BOTTOM = "─"
private const val CELL_BOTTOM = "$ROW_BOTTOM$ROW_BOTTOM$ROW_BOTTOM"

open class Grid(val rows: Int, val columns: Int) {

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

    fun eachRow(executor: (row: List<Cell>) -> Unit) {
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
        var display = ROW_CORNER + (CELL_BOTTOM + ROW_CORNER).repeat(columns) + "\n"

        eachRow { row -> display += rowToString(row) }

        return display.trim()
    }

    private fun rowToString(row: List<Cell>): String {
        var rowTop = ROW_SIDE
        var rowBottom = ROW_CORNER
        row.forEach { cell ->
            val cellEastBoundary = if (cell.isLinkedTo(cell.east)) " " else ROW_SIDE
            rowTop += " ${cellContentsFor(cell)} $cellEastBoundary"
            val cellSouthBoundary = if (cell.isLinkedTo(cell.south)) "   " else CELL_BOTTOM
            rowBottom += cellSouthBoundary + ROW_CORNER
        }
        return rowTop + "\n" + rowBottom + "\n"
    }

    open fun cellContentsFor(cell: Cell): String = " "

    open fun backgroundColourFor(cell: Cell): TextStyle? {
        return null
    }

    init {
        this.configureCells()
    }
}
