import org.junit.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class DistanceGridTest {

    @Test
    fun displaysACellsDistance() {
        val grid = DistanceGrid(3, 4)
        val root = Cell(1, 2)
        val linkedCell = Cell(2, 3)
        root.link(linkedCell)
        grid.distances = root.distances()

        assertNotNull(grid.cellContentsFor(root))
        assertEquals("0", grid.cellContentsFor(root))
        assertEquals("1", grid.cellContentsFor(linkedCell))
    }

    @Ignore
    @Test
    fun displaysDistancesAsLettersToKeepUniformWidth() {
        val grid = DistanceGrid(3, 4)
        val root = Cell(1, 2)
        val linkedCell = Cell(2, 3)
        root.link(linkedCell)
        linkedCell.distances().set(root, 10)
        grid.distances = root.distances()

        assertEquals("A", grid.cellContentsFor(linkedCell))
    }

}