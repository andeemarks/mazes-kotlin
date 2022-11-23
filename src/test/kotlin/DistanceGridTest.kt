import org.junit.Test
import kotlin.test.assertContains
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

        assertContains(grid.cellContentsFor(root), "0")
        assertContains(grid.cellContentsFor(linkedCell), "1")
    }

    @Test
    fun displaysDistancesOver9AsLettersToKeepUniformWidth() {
        val grid = DistanceGrid(10, 10)

        assertEquals("9", grid.distanceAsSingleChar(9))
        assertEquals("a", grid.distanceAsSingleChar(10))
        assertEquals("f", grid.distanceAsSingleChar(15))
    }

    @Test
    fun gridsUnderstandCellColourBasedOnDistance() {
        val tree = BinaryTree()
        val grid: DistanceGrid = tree.on(DistanceGrid(10, 10)) as DistanceGrid

        val distances = grid.at(0, 0).distances()
        grid.distances = distances

        assertNotNull(grid.styleFor(grid.randomCell()))
    }
}