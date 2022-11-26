import algos.BinaryTree
import com.github.ajalt.colormath.model.RGB
import com.github.ajalt.mordant.rendering.TextColors
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DistanceGridTest {

    @Test
    fun displaysACellsDistance() {
        val grid = DistanceGrid(3, 4, TextColors.red)
        val root = Cell(1, 2)
        val linkedCell = Cell(2, 3)
        root.link(linkedCell)
        grid.distances = root.distances()

        assertContains(grid.cellContentsFor(root), "0")
        assertContains(grid.cellContentsFor(linkedCell), "1")
    }

    @Test
    fun displaysDistancesOver9AsLettersToKeepUniformWidth() {
        val grid = DistanceGrid(10, 10, TextColors.red)

        assertEquals(" 9 ", grid.distanceAsSingleChar(9))
        assertEquals(" a ", grid.distanceAsSingleChar(10))
        assertEquals(" f ", grid.distanceAsSingleChar(15))
        assertEquals(" 15", grid.distanceAsSingleChar(40))
        assertEquals("100", grid.distanceAsSingleChar(35 * 35))
    }

    @Test
    fun gridsRejectUnsupportedStyles() {
        assertThrows(IllegalArgumentException::class.java) { DistanceGrid(10, 10, TextColors.brightBlue) }
        assertThrows(IllegalArgumentException::class.java) { DistanceGrid(10, 10, TextColors.gray) }
    }

    @Test
    fun gridsFormatCellBGColourBasedOnStyle() {
        var rgb = cellBGColourForStyle(TextColors.red)

        assertTrue(rgb.r > 0)
        assertEquals(0.0f, rgb.g)
        assertEquals(0.0f, rgb.b)

        rgb = cellBGColourForStyle(TextColors.green)

        assertTrue(rgb.g > 0)
        assertEquals(0.0f, rgb.r)
        assertEquals(0.0f, rgb.b)

        rgb = cellBGColourForStyle(TextColors.blue)

        assertTrue(rgb.b > 0)
        assertEquals(0.0f, rgb.r)
        assertEquals(0.0f, rgb.g)

        rgb = cellBGColourForStyle(TextColors.magenta)

        assertTrue(rgb.b > 0)
        assertTrue(rgb.r > 0)
        assertEquals(0.0f, rgb.g)
        assertEquals(rgb.r, rgb.b)

        rgb = cellBGColourForStyle(TextColors.yellow)

        assertTrue(rgb.g > 0)
        assertTrue(rgb.r > 0)
        assertEquals(0.0f, rgb.b)
        assertEquals(rgb.r, rgb.g)
    }

    private fun cellBGColourForStyle(style: TextColors): RGB {
        val grid: DistanceGrid = BinaryTree().on(DistanceGrid(10, 10, style)) as DistanceGrid

        val distances = grid.at(0, 0).distances()
        grid.distances = distances

        return grid.styleFor(grid.randomCell()).bgColor!!.toSRGB()
    }
}