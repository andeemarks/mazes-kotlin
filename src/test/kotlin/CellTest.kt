import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class CellTest {

    @Rule
    @JvmField
    var expectedException: ExpectedException = ExpectedException.none()

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
//    @Test
//    fun errorTestingForChangeSmallerThanTheSmallestCoin() {
//        val changeCalculator = Cell(listOf(5, 10))
//
//        expectedException.expect(IllegalArgumentException::class.java)
//        expectedException.expectMessage("The total 3 cannot be represented in the given currency.")
//
//        changeCalculator.computeMostEfficientChange(3)
//    }
}