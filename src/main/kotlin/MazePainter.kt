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
            val formattedCellPieces = if (grid is DistanceGrid) {
                formatCell(cell, cellContentsFor(cell, grid.distances!!), styleFor(cell, grid.distances!!))
            } else {
                formatCell(cell, "   ")
            }
            rowTop += formattedCellPieces.first
            rowMiddle += formattedCellPieces.second
            rowBottom += formattedCellPieces.third
        }
        return "$rowTop\n$rowMiddle\n$rowBottom\n"
    }

    private fun formatCell(cell: Cell, contents: String, style: TextStyle = white.bg): Triple<String, String, String> {
        if (cell is NullCell) {
            return formatNullCell()
        }

        val cellNorthBoundary = if (cell.isLinkedTo(cell.north)) "   " else "▔".repeat(3)
        val rowTop = style("▛$cellNorthBoundary▜")
        val cellEastBoundary = if (cell.isLinkedTo(cell.east)) " " else "▕"
        val cellWestBoundary = if (cell.isLinkedTo(cell.west)) " " else "▏"
        val rowMiddle = style("$cellWestBoundary$contents$cellEastBoundary")
        val cellSouthBoundary = if (cell.isLinkedTo(cell.south)) "   " else "▁".repeat(3)
        val rowBottom = style("▙$cellSouthBoundary▟")

        return Triple(rowTop, rowMiddle, rowBottom)
    }

    private fun formatNullCell(): Triple<String, String, String> {
        return Triple(black.bg("     "), black.bg("     "), black.bg("     "))
    }

    fun cellContentsFor(cell: Cell, distances: Distances): String {
        distances.distanceFor(cell)?.let {
            return distanceAsSingleChar(distances.distanceFor(cell)!!)
        }

        return "   "
    }

    fun distanceAsSingleChar(distance: Int): String {
        val formattedDistance = distance.toUInt().toString(35)

        if (formattedDistance.length == 1) {
            return " $formattedDistance "
        }

        if (formattedDistance.length == 2) {
            return " $formattedDistance"
        }

        return formattedDistance
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