import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.G
import com.github.nwillc.ksvg.elements.SVG
import java.io.FileWriter
import kotlin.math.roundToInt

private const val CELL_SIZE = 10

class ImageMazePainter(private val grid: Grid) {

    fun paint(fileName: String = "maze.svg") {
        renderGrid(fileName)
    }

    private fun renderGrid(fileName: String) {
        val svg = SVG.svg(true) {
            height = (grid.rows * CELL_SIZE).toString()
            width = (grid.columns * CELL_SIZE).toString()
            style {
                body = """

                 svg .black-stroke { stroke: black; stroke-width: 1; }
                 svg .white-stroke { stroke: white; stroke-width: 1; }

             """.trimIndent()
            }
            grid.eachCell { cell -> cell(cell) }
        }

        FileWriter(fileName).use { svg.render(it, RenderMode.FILE) }
    }

    private fun Container.cell(cell: Cell) {
        val cellX = cell.column * CELL_SIZE
        val cellY = cell.row * CELL_SIZE
        val nextCellX = (cell.column + 1) * CELL_SIZE
        val nextCellY = (cell.row + 1) * CELL_SIZE

        g {
            id = "cell"
            rect {
                cssClass = "white-stroke"
                width = "${nextCellX - cellX}"
                height = "${nextCellY - cellY}"
                x = "$cellX"
                y = "$cellY"
                fill = "white"
            }
            if (cell !is NullCell) {
                rect {
                    cssClass = "black-stroke"
                    width = "${nextCellX - cellX}"
                    height = "${nextCellY - cellY}"
                    x = "$cellX"
                    y = "$cellY"
                    fill = styleFor(cell)
                }
            }
            g {
                id = "doors"
                cssClass = "black-stroke"
                if (!cell.isLinkedTo(cell.east)) {
                    verticalWall(nextCellX, cellY)
                }
                if (!cell.isLinkedTo(cell.west)) {
                    verticalWall(cellX, cellY)
                }
                if (!cell.isLinkedTo(cell.north)) {
                    horizontalWall(cellX, cellY)
                }
                if (!cell.isLinkedTo(cell.south)) {
                    horizontalWall(cellX, nextCellY)
                }
            }
        }
    }

    private fun G.horizontalWall(cellX: Int, cellY: Int) {
        rect {
            width = "11"
            height = "1"
            x = "$cellX"
            y = "$cellY"
            fill = "black"
        }
    }

    private fun G.verticalWall(cellX: Int, cellY: Int) {
        rect {
            width = "1"
            height = "11"
            x = "$cellX"
            y = "$cellY"
            fill = "black"
        }
    }

    private fun styleFor(cell: Cell): String {
        if (grid is DistanceGrid) {
            val distances = grid.distances!!
            val distance = distances.distanceFor(cell) ?: return "#ffffff"
            val maximumDistance = distances.maxDistance().second
            val intensity = (maximumDistance - distance).toFloat() / maximumDistance
            val dark = Integer.toHexString((255 * intensity).roundToInt())
            val bright = Integer.toHexString(128 + (127 * intensity).roundToInt())

            return "#${dark}${bright}${dark}"
        } else {
            return "#ff0000"
        }
    }

}