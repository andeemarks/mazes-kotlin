import org.junit.Test
import kotlin.test.assertEquals

class DistancesTest {

    @Test
    fun distancesStartWithARootCell() {
        val rootCell = Cell(0, 0)
        val distances = Distances(rootCell)

        assertEquals(rootCell, distances.root)
    }

    @Test
    fun rootCellIsAlwaysDistanceOf0() {
        val rootCell = Cell(0, 0)
        val distances = Distances(rootCell)

        assertEquals(0, distances.forCell(rootCell))
    }

    @Test
    fun distancesCanBeSet() {
        val rootCell = Cell(0, 0)
        val distances = Distances(rootCell)

        val newCell = Cell(1, 1)
        distances.set(newCell, 3)

        assertEquals(3, distances.forCell(newCell))
    }
}