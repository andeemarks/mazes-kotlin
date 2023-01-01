import org.junit.Test
import kotlin.test.assertEquals

class DistanceGridTest {

    @Test
    fun formatsCellsDistanceForReducedWidth() {
        val grid = DistanceGrid(4, 5)

        assertEquals(" 9 ", grid.format(9))
        assertEquals(" a ", grid.format(10))
        assertEquals(" f ", grid.format(15))
        assertEquals(" 15", grid.format(40))
        assertEquals("100", grid.format(35 * 35))
    }
}