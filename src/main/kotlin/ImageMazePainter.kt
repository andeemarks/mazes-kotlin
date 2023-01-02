import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.G
import com.github.nwillc.ksvg.elements.SVG
import java.io.FileWriter
import kotlin.math.roundToInt

class ImageMazePainter(private val grid: Grid) {

    fun paint(fileName: String = "maze.svg") {
        renderGrid(fileName)
    }

    private fun renderGrid(fileName: String) {
        val svg = SVG.svg(true) {
            height = (grid.rows * 10).toString()
            width = (grid.columns * 10).toString()
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
        val cellX = cell.column * 10
        val cellY = cell.row * 10
        g {
            id = "cell"
            rect {
                cssClass = "white-stroke"
                width = "11"
                height = "11"
                x = "$cellX"
                y = "$cellY"
                fill = "white"
            }
            if (cell !is NullCell) {
                rect {
                    cssClass = "black-stroke"
                    width = "11"
                    height = "11"
                    x = "$cellX"
                    y = "$cellY"
                    fill = styleFor(cell)
                }
            }
            g {
                id = "doors"
                cssClass = "black-stroke"
                if (!cell.isLinkedTo(cell.east)) {
                    eastWall(cellX, cellY)
                }
                if (!cell.isLinkedTo(cell.west)) {
                    westWall(cellX, cellY)
                }
                if (!cell.isLinkedTo(cell.north)) {
                    northWall(cellX, cellY)
                }
                if (!cell.isLinkedTo(cell.south)) {
                    southWall(cellX, cellY)
                }
            }
        }
    }

    private fun G.southWall(cellX: Int, cellY: Int) {
        rect {
            id = "south-wall"
            width = "10"
            height = "1"
            x = "$cellX"
            y = "${cellY + 10}"
            fill = "black"
        }
    }

    private fun G.northWall(cellX: Int, cellY: Int) {
        rect {
            id = "north-wall"
            width = "10"
            height = "1"
            x = "$cellX"
            y = "$cellY"
            fill = "black"
        }
    }

    private fun G.westWall(cellX: Int, cellY: Int) {
        rect {
            id = "west-wall"
            width = "1"
            height = "10"
            x = "$cellX"
            y = "$cellY"
            fill = "black"
        }
    }

    private fun G.eastWall(cellX: Int, cellY: Int) {
        rect {
            id = "east-wall"
            width = "1"
            height = "10"
            x = "${cellX + 10}"
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