import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.G
import com.github.nwillc.ksvg.elements.SVG
import java.io.FileWriter
import kotlin.math.roundToInt

private const val CELL_SIZE = 10

class ImageMazePainter(private val grid: Grid) {

    fun paint(fileName: String = "maze.svg") {
        val svg = generateSVG()

        FileWriter(fileName).use { svg.render(it, RenderMode.FILE) }
    }

    private fun generateSVG(): SVG {
        return SVG.svg(true) {
            height = (grid.rows * CELL_SIZE).toString()
            width = (grid.columns * CELL_SIZE).toString()
            grid.eachCell { cell -> cell(cell) }
        }
    }

    private fun Container.cell(cell: Cell) {
        val cellX = cell.column * CELL_SIZE
        val cellY = cell.row * CELL_SIZE
        val nextCellX = (cell.column + 1) * CELL_SIZE
        val nextCellY = (cell.row + 1) * CELL_SIZE

        g {
            cell(cell, cellX, cellY, nextCellX - cellX, nextCellY - cellY)
            g {
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

    private fun G.cell(cell: Cell, cellX: Int, cellY: Int, cellWidth: Int, cellHeight: Int) {
        rect {
            width = "$cellWidth"
            height = "$cellHeight"
            x = "$cellX"
            y = "$cellY"
            fill = fillColourFor(cell)
        }
    }

    private fun G.horizontalWall(cellX: Int, cellY: Int) {
        wall(cellX, cellY, 11, 1)
    }

    private fun G.verticalWall(cellX: Int, cellY: Int) {
        wall(cellX, cellY, 1, 11)
    }

    private fun G.wall(cellX: Int, cellY: Int, wallWidth: Int, wallHeight: Int) {
        rect {
            width = "$wallWidth"
            height = "$wallHeight"
            x = "$cellX"
            y = "$cellY"
            fill = "black"
        }
    }

    private fun fillColourFor(cell: Cell): String {
        if (cell is NullCell) {
            return "black"
        }

        if (grid is DistanceGrid) {
            val distances = grid.distances!!
            val distance = distances.distanceFor(cell) ?: return "#ffffff"
            val maximumDistance = distances.maxDistance().second
            val intensity = (maximumDistance - distance).toFloat() / maximumDistance
            val dark = (255 * intensity).roundToInt()
            val bright = 128 + (127 * intensity).roundToInt()

            return rgbToHexString(dark, bright, dark)
        } else {
            return "#ff0000"
        }
    }

    private fun rgbToHexString(red: Int, green: Int, blue: Int) =
        "#${Integer.toHexString(red)}${Integer.toHexString(green)}${Integer.toHexString(blue)}"

}