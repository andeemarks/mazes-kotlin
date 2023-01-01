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
        grid.eachRow { row -> display += rowToString(grid, row) }

        return display.trim()
    }

    private fun rowToString(grid: Grid, row: List<Cell>): String {
        var rowTop = ""
        var rowBottom = ""
        var rowMiddle = ""
        row.forEach { cell ->
            val formattedCell = if (grid is DistanceGrid) {
                formatDistanceCell(cell, grid)
            } else if (cell is NullCell) {
                formatNullCell()
            } else {
                formatRegularCell(cell)
            }

            rowTop += formattedCell.first
            rowMiddle += formattedCell.second
            rowBottom += formattedCell.third
        }
        return "$rowTop\n$rowMiddle\n$rowBottom\n"
    }

    private fun formatRegularCell(cell: Cell): Triple<String, String, String> {
        return formatCell(cell, "   ", white.bg)
    }

    private fun formatDistanceCell(cell: Cell, grid: DistanceGrid): Triple<String, String, String> {
        return formatCell(cell, cellContentsFor(cell, grid), styleFor(cell, grid.distances!!))
    }

    private fun formatNullCell(): Triple<String, String, String> {
        return Triple(black.bg("     "), black.bg("     "), black.bg("     "))
    }

    private fun formatCell(cell: Cell, contents: String, style: TextStyle): Triple<String, String, String> {
        val cellNorthBoundary = if (cell.isLinkedTo(cell.north)) "   " else "▔".repeat(3)
        val rowTop = style("▛$cellNorthBoundary▜")
        val cellEastBoundary = if (cell.isLinkedTo(cell.east)) " " else "▕"
        val cellWestBoundary = if (cell.isLinkedTo(cell.west)) " " else "▏"
        val rowMiddle = style("$cellWestBoundary$contents$cellEastBoundary")
        val cellSouthBoundary = if (cell.isLinkedTo(cell.south)) "   " else "▁".repeat(3)
        val rowBottom = style("▙$cellSouthBoundary▟")

        return Triple(rowTop, rowMiddle, rowBottom)
    }

    fun cellContentsFor(cell: Cell, grid: DistanceGrid): String {
        val distances = grid.distances!!
        distances.distanceFor(cell)?.let {
            return grid.distanceAsSingleChar(distances.distanceFor(cell)!!)
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