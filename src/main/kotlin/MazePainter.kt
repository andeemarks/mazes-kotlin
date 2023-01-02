import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle
import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.SVG
import java.io.FileWriter

private const val EMPTY_CELL_CONTENTS = "   "

class MazePainter(private val grid: Grid, private val cellColour: TextColors = green) {

    init {
        require(
            cellColour in listOf(red, blue, green, magenta, yellow)
        ) { "Color must be one of red, green, blue, yellow or magenta" }
    }

    fun paint(): String {
        var display = ""
        grid.eachRow { row -> display += formatRow(row) }
        renderGrid()

        return display.trim()
    }

    private fun renderGrid() {
        val svg = SVG.svg(true) {
            height = (grid.rows * 10).toString()
            width = (grid.columns * 10).toString()
            style {
                body = """

                 svg .black-stroke { stroke: black; stroke-width: 1; }
                 svg .white-stroke { stroke: white; stroke-width: 1; }
                 svg .fur-color { fill: white; }

             """.trimIndent()
            }
            grid.eachCell { cell -> cell(cell) }
        }

        FileWriter("maze.svg").use { svg.render(it, RenderMode.FILE) }
    }

    private fun Container.cell(cell: Cell) {
        g {
            id = "cell"
            rect {
                cssClass = "white-stroke"
                id = "cell"
                width = "10"
                height = "10"
                x = "${cell.column * 10}"
                y = "${cell.row * 10}"
                fill = "white"
            }
            if (cell !is NullCell) {
                rect {
                    cssClass = "black-stroke"
                    id = "cell"
                    width = "8"
                    height = "8"
                    x = "${cell.column * 10 + 1}"
                    y = "${cell.row * 10 + 1}"
                }
            }
            g {
                id = "doors"
                cssClass = "black-stroke"
                if (cell.isLinkedTo(cell.east)) {
                    rect {
                        id = "east-door"
                        width = "2"
                        height = "8"
                        x = "${cell.column * 10 + 10}"
                        y = "${cell.row * 10 + 1}"
                        fill = "black"
                    }
                }
                if (cell.isLinkedTo(cell.west)) {
                    rect {
                        id = "west-door"
                        width = "2"
                        height = "8"
                        x = "${cell.column * 10 - 1}"
                        y = "${cell.row * 10 + 1}"
                        fill = "black"
                    }
                }
                if (cell.isLinkedTo(cell.north)) {
                    rect {
                        id = "north-door"
                        width = "8"
                        height = "2"
                        x = "${cell.column * 10 + 1}"
                        y = "${cell.row * 10 - 1}"
                        fill = "black"
                    }
                }
                if (cell.isLinkedTo(cell.south)) {
                    rect {
                        id = "south-door"
                        width = "8"
                        height = "2"
                        x = "${cell.column * 10 + 1}"
                        y = "${cell.row * 10 + 10}"
                        fill = "black"
                    }
                }
            }
        }
    }

    private fun formatRow(row: List<Cell>): String {
        val formattedRow = FormattedRow()
        row.forEach { cell ->
            val formattedCell = when {
                grid is DistanceGrid -> formatDistanceCell(cell)
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
        return formatCell(cell, EMPTY_CELL_CONTENTS, white.bg)
    }

    private fun formatDistanceCell(cell: Cell): FormattedCell {
        return formatCell(cell, contentsFor(cell), styleFor(cell))
    }

    private fun formatNullCell(): FormattedCell {
        val contents = "     "
        return FormattedCell(black.bg(contents), black.bg(contents), black.bg(contents))
    }

    private fun formatCell(cell: Cell, contents: String, style: TextStyle): FormattedCell {
        val cellNorthBoundary = if (cell.isLinkedTo(cell.north)) EMPTY_CELL_CONTENTS else "▔".repeat(3)
        val rowTop = style("▛$cellNorthBoundary▜")
        val cellEastBoundary = if (cell.isLinkedTo(cell.east)) " " else "▕"
        val cellWestBoundary = if (cell.isLinkedTo(cell.west)) " " else "▏"
        val rowMiddle = style("$cellWestBoundary$contents$cellEastBoundary")
        val cellSouthBoundary = if (cell.isLinkedTo(cell.south)) EMPTY_CELL_CONTENTS else "▁".repeat(3)
        val rowBottom = style("▙$cellSouthBoundary▟")

        return FormattedCell(rowTop, rowMiddle, rowBottom)
    }

    fun contentsFor(cell: Cell): String {
        val distances = (grid as DistanceGrid).distances!!
        distances.distanceFor(cell)?.let {
            return grid.format(distances.distanceFor(cell)!!)
        }

        return EMPTY_CELL_CONTENTS
    }

    fun styleFor(cell: Cell): TextStyle {
        val distances = (grid as DistanceGrid).distances!!
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