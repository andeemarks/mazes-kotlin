import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle

class MazePainter(private val cellColour: TextColors = green) {

    init {
        require(
            cellColour in listOf(
                red,
                blue,
                green,
                magenta,
                yellow
            )
        ) { "Color must be one of red, green, blue, yellow or magenta" }
    }


    fun paint(grid: Grid): String {
        var display = ""
        grid.eachRow { row -> display += formatRow(grid, row) }

        return display.trim()
    }

    private fun formatRow(grid: Grid, row: List<Cell>): String {
        val formattedRow = FormattedRow()
        row.forEach { cell ->
            val formattedCell = when {
                grid is DistanceGrid -> formatDistanceCell(cell, grid)
                cell is NullCell -> formatNullCell()
                else -> formatRegularCell(cell)
            }

            formattedRow.addCell(formattedCell)
        }
        return formattedRow.toString()
    }

    data class FormattedRow(
        private var top: String = "",
        private var middle: String = "",
        private var bottom: String = ""
    ) {
        fun addCell(cell: FormattedCell) {
            top += cell.top
            middle += cell.middle
            bottom += cell.bottom
        }

        override fun toString(): String = "$top\n$middle\n$bottom\n"
    }

    data class FormattedCell(val top: String, val middle: String, val bottom: String)

    private fun formatRegularCell(cell: Cell): FormattedCell {
        return formatCell(cell, "   ", white.bg)
    }

    private fun formatDistanceCell(cell: Cell, grid: DistanceGrid): FormattedCell {
        return formatCell(cell, cellContentsFor(cell, grid), styleFor(cell, grid.distances!!))
    }

    private fun formatNullCell(): FormattedCell {
        return FormattedCell(black.bg("     "), black.bg("     "), black.bg("     "))
    }

    private fun formatCell(cell: Cell, contents: String, style: TextStyle): FormattedCell {
        val cellNorthBoundary = if (cell.isLinkedTo(cell.north)) "   " else "▔".repeat(3)
        val rowTop = style("▛$cellNorthBoundary▜")
        val cellEastBoundary = if (cell.isLinkedTo(cell.east)) " " else "▕"
        val cellWestBoundary = if (cell.isLinkedTo(cell.west)) " " else "▏"
        val rowMiddle = style("$cellWestBoundary$contents$cellEastBoundary")
        val cellSouthBoundary = if (cell.isLinkedTo(cell.south)) "   " else "▁".repeat(3)
        val rowBottom = style("▙$cellSouthBoundary▟")

        return FormattedCell(rowTop, rowMiddle, rowBottom)
    }

    fun cellContentsFor(cell: Cell, grid: DistanceGrid): String {
        val distances = grid.distances!!
        distances.distanceFor(cell)?.let {
            return grid.format(distances.distanceFor(cell)!!)
        }

        return "   "
    }

    fun styleFor(cell: Cell, distances: Distances): TextStyle {
        val distance = distances.distanceFor(cell) ?: return white
        val maximumDistance = distances.maxDistance().second
        val intensity = (maximumDistance - distance).toFloat() / maximumDistance

        return when (cellColour) {
            red -> TextColors.rgb(intensity, 0, 0).bg
            green -> TextColors.rgb(0, intensity, 0).bg
            blue -> TextColors.rgb(0, 0, intensity).bg
            magenta -> TextColors.rgb(intensity, 0, intensity).bg
            yellow -> TextColors.rgb(intensity, intensity, 0).bg
            else -> throw IllegalArgumentException()
        }
    }

}