import org.junit.Test
import kotlin.test.assertEquals

class DistanceGridTest {

    @Test
    fun displaysACellsDistance() {
        val grid = DistanceGrid(3, 4)
        val root = Cell(1, 2)
        val linkedCell = Cell(2, 3)
        root.link(linkedCell)
        grid.distances = root.distances()

        assertEquals("0", grid.cellContentsFor(root))
        assertEquals("1", grid.cellContentsFor(linkedCell))
    }

    @Test
    fun displaysDistancesOver9AsLettersToKeepUniformWidth() {
        val grid = DistanceGrid(10, 10)

        assertEquals("9", grid.distanceAsSingleChar(9))
        assertEquals("a", grid.distanceAsSingleChar(10))
        assertEquals("f", grid.distanceAsSingleChar(15))
    }

}