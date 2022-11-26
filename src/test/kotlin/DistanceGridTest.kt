import algos.BinaryTree
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
        assertThrows(IllegalArgumentException::class.java) { DistanceGrid(10, 10, TextColors.yellow) }
        assertThrows(IllegalArgumentException::class.java) { DistanceGrid(10, 10, TextColors.brightBlue) }
        assertThrows(IllegalArgumentException::class.java) { DistanceGrid(10, 10, TextColors.gray) }
    }

    @Test
    fun gridsFormatCellBGColourBasedOnStyle() {
        val tree = BinaryTree()

        var grid = createGridWithStyle(tree, TextColors.red)
        var style = grid.styleFor(grid.randomCell())

        assertTrue(style.bgColor!!.toSRGB().r > 0)
        assertEquals(0.0f, style.bgColor!!.toSRGB().g)
        assertEquals(0.0f, style.bgColor!!.toSRGB().b)

        grid = createGridWithStyle(tree, TextColors.green)
        style = grid.styleFor(grid.randomCell())

        assertTrue(style.bgColor!!.toSRGB().g > 0)
        assertEquals(0.0f, style.bgColor!!.toSRGB().r)
        assertEquals(0.0f, style.bgColor!!.toSRGB().b)

        grid = createGridWithStyle(tree, TextColors.blue)
        style = grid.styleFor(grid.randomCell())

        assertTrue(style.bgColor!!.toSRGB().b > 0)
        assertEquals(0.0f, style.bgColor!!.toSRGB().r)
        assertEquals(0.0f, style.bgColor!!.toSRGB().g)
    }

    private fun createGridWithStyle(tree: BinaryTree, style: TextColors): DistanceGrid {
        val grid: DistanceGrid = tree.on(DistanceGrid(10, 10, style)) as DistanceGrid

        val distances = grid.at(0, 0).distances()
        grid.distances = distances

        return grid
    }
}