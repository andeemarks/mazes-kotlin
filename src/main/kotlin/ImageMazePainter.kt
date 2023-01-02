import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.G
import com.github.nwillc.ksvg.elements.SVG
import java.io.FileWriter

class ImageMazePainter(private val grid: Grid) {

    fun paint() {
        renderGrid()
    }

    private fun renderGrid() {
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

        FileWriter("maze.svg").use { svg.render(it, RenderMode.FILE) }
    }

    private fun Container.cell(cell: Cell) {
        val cellX = cell.column * 10
        val cellY = cell.row * 10
        g {
            id = "cell"
            rect {
                cssClass = "white-stroke"
                width = "10"
                height = "10"
                x = "$cellX"
                y = "$cellY"
                fill = "white"
            }
            if (cell !is NullCell) {
                rect {
                    cssClass = "black-stroke"
                    width = "8"
                    height = "8"
                    x = "${cellX + 1}"
                    y = "${cellY + 1}"
                }
            }
            g {
                id = "doors"
                cssClass = "black-stroke"
                if (cell.isLinkedTo(cell.east)) {
                    eastDoor(cellX, cellY)
                }
                if (cell.isLinkedTo(cell.west)) {
                    westDoor(cellX, cellY)
                }
                if (cell.isLinkedTo(cell.north)) {
                    northDoor(cellX, cellY)
                }
                if (cell.isLinkedTo(cell.south)) {
                    southDoor(cellX, cellY)
                }
            }
        }
    }

    private fun G.southDoor(cellX: Int, cellY: Int) {
        rect {
            id = "south-door"
            width = "8"
            height = "2"
            x = "${cellX + 1}"
            y = "${cellY + 10}"
            fill = "black"
        }
    }

    private fun G.northDoor(cellX: Int, cellY: Int) {
        rect {
            id = "north-door"
            width = "8"
            height = "2"
            x = "${cellX + 1}"
            y = "${cellY - 1}"
            fill = "black"
        }
    }

    private fun G.westDoor(cellX: Int, cellY: Int) {
        rect {
            id = "west-door"
            width = "2"
            height = "8"
            x = "${cellX - 1}"
            y = "${cellY + 1}"
            fill = "black"
        }
    }

    private fun G.eastDoor(cellX: Int, cellY: Int) {
        rect {
            id = "east-door"
            width = "2"
            height = "8"
            x = "${cellX + 10}"
            y = "${cellY + 1}"
            fill = "black"
        }
    }


}