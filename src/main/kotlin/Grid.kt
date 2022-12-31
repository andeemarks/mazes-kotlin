import com.github.ajalt.mordant.rendering.TextStyle
import kotlin.random.Random

open class Grid(val rows: Int, val columns: Int) {
    open val size: Int = this.rows * this.columns
    var cells: List<MutableList<Cell>> =
        MutableList(rows) { row -> MutableList(columns) { column -> Cell(row, column) } }

    init {
        this.configureCells()
    }

    open fun configureCells() {
        eachCell(::configureCell)
    }

    private fun configureCell(cell: Cell): Cell {
        cell.north = internalAt(cell.row - 1, cell.column)
        cell.south = internalAt(cell.row + 1, cell.column)
        cell.east = internalAt(cell.row, cell.column + 1)
        cell.west = internalAt(cell.row, cell.column - 1)

        return cell
    }

    open fun randomCell(): Cell {
        return at(Random.nextInt(rows), Random.nextInt(columns))
    }

    open fun cellContentsFor(cell: Cell): String = "   "
    open fun styleFor(cell: Cell): TextStyle? = null

    fun at(row: Int, column: Int): Cell {
        require((0 until rows).contains(row))
        require((0 until columns).contains(column))

        return cells[row][column]
    }

    private fun internalAt(row: Int, column: Int): Cell? {
        return try {
            at(row, column)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    fun eachCell(executor: (cell: Cell) -> Unit) {
        cells.forEach { row -> row.forEach { cell -> executor(cell) } }
    }

    fun eachRow(executor: (row: List<Cell>) -> Unit) {
        cells.forEach { row -> executor(row) }
    }

    override fun toString(): String {
        var display = ""
        eachRow { row -> display += rowToString(row) }

        return display.trim()
    }

    private fun rowToString(row: List<Cell>): String {
        var rowTop = ""
        var rowBottom = ""
        var rowMiddle = ""
        row.forEach { cell ->
            val formattedCellPieces = cell.formatCell(styleFor(cell), cellContentsFor(cell))
            rowTop += formattedCellPieces.first
            rowMiddle += formattedCellPieces.second
            rowBottom += formattedCellPieces.third
        }
        return "$rowTop\n$rowMiddle\n$rowBottom\n"
    }

    fun deadEndCells(): List<Cell> {
        val deadEndCells = mutableListOf<Cell>()
        eachCell { cell -> if (cell.links.size == 1) deadEndCells.add(cell) }

        return deadEndCells
    }
}
