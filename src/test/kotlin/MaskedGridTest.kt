import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class MaskedGridTest {

    @Test
    fun isATypeOfGrid() {
        assertIs<Grid>(MaskedGrid(Mask(1, 1)))
    }

    @Test
    fun basedTheGridDimensionsOffTheMask() {
        val grid = MaskedGrid(Mask(2, 3))

        assertEquals(2, grid.rows)
        assertEquals(3, grid.columns)
    }

    @Test
    fun exposesTheMask() {
        val mask = Mask(2, 3)
        val grid = MaskedGrid(mask)

        assertEquals(mask, grid.mask)
    }

    @Test
    fun exposesTheMaskCount() {
        val mask = Mask(2, 3)
        mask.set(0, 0, false)
        val grid = MaskedGrid(mask)

        assertEquals(mask.count(), grid.size)
    }

    @Test
    fun randomCellsAreMasked() {
        val mask = Mask(1, 2)
        mask.set(0, 0, false)
        val grid = MaskedGrid(mask)

        assertEquals(grid.at(0, 1), grid.randomCell())
    }
}