import algos.BinaryTree
import com.github.ajalt.colormath.model.RGB
import com.github.ajalt.mordant.rendering.TextColors
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MazePainterTest {

    @Test
    fun formatsADistanceGridCellsToOneCharacter() {
        val grid = DistanceGrid(3, 4)
        val root = Cell(1, 2)
        val linkedCell = Cell(2, 3)
        root.link(linkedCell)
        grid.distances = root.distances()

        val painter = AsciiMazePainter(grid)

        assertContains(painter.contentsFor(root), "0")
        assertContains(painter.contentsFor(linkedCell), "1")
    }

    @Test
    fun rejectsUnsupportedStyles() {
        assertThrows(IllegalArgumentException::class.java) { AsciiMazePainter(Grid(2, 3), TextColors.brightBlue) }
        assertThrows(IllegalArgumentException::class.java) { AsciiMazePainter(Grid(2, 3), TextColors.gray) }
    }

    @Test
    fun formatsCellBGColourBasedOnStyle() {
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
        val grid: DistanceGrid = BinaryTree().on(DistanceGrid(10, 10)) as DistanceGrid

        val distances = grid.at(0, 0).distances()
        grid.distances = distances
        val painter = AsciiMazePainter(grid, style)

        return painter.styleFor(grid.randomCell()).bgColor!!.toSRGB()
    }
}