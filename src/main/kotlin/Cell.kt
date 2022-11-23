import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

data class Cell(val row: Int, val column: Int) {
    var west: Cell? = null
    var east: Cell? = null
    var south: Cell? = null
    var north: Cell? = null
    val links: MutableList<Cell> = mutableListOf()

    fun link(cell: Cell) {
        this.links.add(cell)
        cell.links.add(this)
    }

    fun unlink(cell: Cell) {
        this.links.remove(cell)
        cell.links.remove(this)
    }

    fun isLinkedTo(cell: Cell?): Boolean = this.links.contains(cell)

    fun distances(): Distances {
        val distances = Distances(this)
        var frontier = mutableListOf(this)

        while (frontier.isNotEmpty()) {
            val newFrontier = mutableListOf<Cell>()

            frontier.forEach { cell ->
                cell.links.forEach { linkedCell ->
                    if (distances.distanceFor(linkedCell) == null) {
                        distances.set(linkedCell, distances.distanceFor(cell)!! + 1)
                        newFrontier.add(linkedCell)
                    }
                }
            }
            frontier = newFrontier
        }
        return distances
    }

    fun formatCell(style: TextStyle?, contents: String): Triple<String, String, String> {
        val cellStyle = style ?: TextColors.white.bg
        val cellNorthBoundary = if (isLinkedTo(north)) "   " else "▔".repeat(3)
        val rowTop = cellStyle("▛$cellNorthBoundary▜")
        val cellEastBoundary = if (isLinkedTo(east)) " " else "▕"
        val cellWestBoundary = if (isLinkedTo(west)) " " else "▏"
        val rowMiddle = cellStyle("$cellWestBoundary $contents $cellEastBoundary")
        val cellSouthBoundary = if (isLinkedTo(south)) "   " else "▁".repeat(3)
        val rowBottom = cellStyle("▙$cellSouthBoundary▟")

        return Triple(rowTop, rowMiddle, rowBottom)
    }

}
