import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertIs
import kotlin.test.assertTrue

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

        assertEquals(grid.initCell(0, 1), grid.randomCell())
    }

    @Test
    fun preparingGridOnlyPopulatedCellsEnabledInMask() {
        val mask = Mask(1, 2)
        mask.set(0, 0, false)
        val grid = MaskedGrid(mask)

        assertTrue(grid.initCell(0, 0).isEmpty())
        assertFalse(grid.initCell(0, 1).isEmpty())

    }
}