import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

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

    @Test
    fun canFindPathToSameCell() {
        val rootCell = Cell(0, 0)
        val distances = Distances(rootCell)

        val path = distances.pathTo(rootCell)

        assertIs<Distances>(path)
        assertEquals(rootCell, path.root)
    }
    @Test
    fun canFindMultiCellPath() {
        val tree = BinaryTree()
        val grid: DistanceGrid = tree.on(DistanceGrid(3, 4)) as DistanceGrid

        val rootCell = grid.at(0, 0)
        val goalCell = grid.at(2, 3)
        val distances = rootCell!!.distances()

        val path = distances.pathTo(goalCell!!)

        assertEquals(rootCell, path.root)
        assertTrue(path.length() > 1)
    }
}