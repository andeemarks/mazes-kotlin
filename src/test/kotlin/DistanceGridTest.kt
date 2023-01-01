import org.junit.Test
import kotlin.test.assertEquals

class DistanceGridTest {

    @Test
    fun formatsCellsDistanceToOneCharacter() {
        val grid = DistanceGrid(4, 5)

        assertEquals(" 9 ", grid.distanceAsSingleChar(9))
        assertEquals(" a ", grid.distanceAsSingleChar(10))
        assertEquals(" f ", grid.distanceAsSingleChar(15))
        assertEquals(" 15", grid.distanceAsSingleChar(40))
        assertEquals("100", grid.distanceAsSingleChar(35 * 35))
    }
}