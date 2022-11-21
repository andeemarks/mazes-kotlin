import org.junit.Test
import kotlin.test.*

class CellTest {

    @Test
    fun cellsRememberTheirLocation() {
        val cell = Cell(3, 4)
        assertEquals(3, cell.row)
        assertEquals(4, cell.column)
    }

    @Test
    fun cellsStartWithEmptyLinks() {
        val cell = Cell(3, 4)
        assertEquals(0, cell.links.size)
    }

    @Test
    fun cellsCanBeLinked() {
        val cell = Cell(3, 4,)
        val otherCell = Cell(2, 1)

        cell.link(otherCell)

        assertEquals(otherCell, cell.links.first())
    }

    @Test
    fun cellLinksAreBidirectional() {
        val cell = Cell(3, 4,)
        val otherCell = Cell(2, 1)

        cell.link(otherCell)

        assertEquals(cell, otherCell.links.first())
    }

    @Test
    fun linksCanBeBroken() {
        val cell = Cell(3, 4,)
        val otherCell = Cell(2, 1)

        cell.link(otherCell)
        cell.unlink(otherCell)

        assertEquals(0, cell.links.size)
    }

    @Test
    fun linksAreBrokenBidiredctionally() {
        val cell = Cell(3, 4,)
        val otherCell = Cell(2, 1)

        cell.link(otherCell)
        cell.unlink(otherCell)

        assertEquals(0, otherCell.links.size)
    }

    @Test
    fun cellsCanBeQueriedForALink() {
        val cell = Cell(3, 4,)
        val otherCell = Cell(2, 1)

        cell.link(otherCell)

        assertTrue(cell.isLinkedTo(otherCell))

        cell.unlink(otherCell)

        assertFalse(cell.isLinkedTo(otherCell))
        assertFalse(cell.isLinkedTo(null))
    }

    @Test
    fun cellsInDifferentLocationsAreNotEqual() {
        assertNotEquals(Cell(0, 0), Cell(1,1))
    }

    @Test
    fun cellsInTheSameLocationAreEqual() {
        assertEquals(Cell(2, 1), Cell(2,1))
    }

    @Test
    fun cellsKnowTheDistanceToTheirLinkedCells() {
        val cell = Cell(0, 0)
        val linkedCell = Cell(2, 1)
        val anotherLinkedCell = Cell(2, 3)
        val nonLinkedCell = Cell(2, 2)

        cell.link(linkedCell)
        cell.link(anotherLinkedCell)

        assertNotNull(cell.distances())
        assertEquals(1, cell.distances().forCell(linkedCell))
        assertEquals(1, cell.distances().forCell(anotherLinkedCell))
        assertNull(cell.distances().forCell(nonLinkedCell))
    }
}