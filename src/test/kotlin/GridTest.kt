import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class GridTest {

    @Test
    fun gridsRememberTheirDimensions() {
        val grid = Grid(3, 4)
        assertEquals(3, grid.rows)
        assertEquals(4, grid.columns)
    }

    @Test
    fun gridsStartWithPopulatedCells() {
        val grid = Grid(2, 1)
        assertNotNull(grid.cells)
        assertEquals(grid.cells[0][0], Cell(0, 0))
        assertEquals(grid.cells[1][0], Cell(1, 0))
    }

    @Test
    fun gridsCanReportOnACell() {
        val grid = Grid(2, 1)
        val cell = grid.at(1, 0)

        assertNotNull(cell)
    }

    @Test
    fun gridsReturnNullForCellsBeyondItsDimension() {
        val grid = Grid(2, 1)

        assertFailsWith<IllegalArgumentException> { grid.at(2, 0) }
        assertFailsWith<IllegalArgumentException> { grid.at(0, 2) }
        assertFailsWith<IllegalArgumentException> { grid.at(-1, 0) }
        assertFailsWith<IllegalArgumentException> { grid.at(0, -1) }
    }

    @Test
    fun gridsKnowTheirSize() {

        assertEquals(42, Grid(6, 7).size)
        assertEquals(0, Grid(0, 0).size)
    }

    @Test
    fun gridsCanReturnARandomCell() {
        val grid = Grid(200, 200)

        val uniqueCells = mutableSetOf<Cell>()
        for (count in 1..50) {
            uniqueCells.add(grid.randomCell())
        }

        assertEquals(50, uniqueCells.size)
    }

    @Test
    fun gridCellsArePopulatedWithNeighboursDuringConstruction() {
        val grid = Grid(3, 3)

        val middleCell = grid.at(1, 1)
        assertEquals(middleCell.north, grid.at(0, 1))
        assertEquals(middleCell.south, grid.at(2, 1))
        assertEquals(middleCell.east, grid.at(1, 2))
        assertEquals(middleCell.west, grid.at(1, 0))
    }

    @Test
    fun normalGridsDoNotUnderstandColour() {
        val grid = Grid(3, 5)

        assertNull(grid.backgroundColourFor(grid.randomCell()))
    }
}