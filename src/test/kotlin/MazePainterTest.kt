import algos.BinaryTree
import com.github.ajalt.colormath.model.RGB
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle
import org.junit.Assert.assertThrows
import org.junit.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MazePainterTest {

    @Test
    fun formatsADistanceGridCellsToOneCharacter() {
        val grid = DistanceGrid(3, 4)
        val root = Cell(1, 2)
        val linkedCell = Cell(2, 3)
        root.link(linkedCell)
        grid.distances = root.distances()

        val painter = MazePainter()

        assertContains(painter.cellContentsFor(root, grid.distances!!), "0")
        assertContains(painter.cellContentsFor(linkedCell, grid.distances!!), "1")
    }

    @Test
    fun formatsCellsDistanceToOneCharacter() {
        val painter = MazePainter()

        assertEquals(" 9 ", painter.distanceAsSingleChar(9))
        assertEquals(" a ", painter.distanceAsSingleChar(10))
        assertEquals(" f ", painter.distanceAsSingleChar(15))
        assertEquals(" 15", painter.distanceAsSingleChar(40))
        assertEquals("100", painter.distanceAsSingleChar(35 * 35))
    }

    @Test
    fun rejectsUnsupportedStyles() {
        assertThrows(IllegalArgumentException::class.java) { MazePainter(TextColors.brightBlue) }
        assertThrows(IllegalArgumentException::class.java) { MazePainter(TextColors.gray) }
    }


    @Test
    fun normalGridsDoNotUnderstandColour() {
        MazePainter()
        assertNull(null as TextStyle?)
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
        val painter = MazePainter(style)

        return painter.styleFor(grid.randomCell(), grid.distances!!).bgColor!!.toSRGB()
    }
}