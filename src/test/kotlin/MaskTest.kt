import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MaskTest {
    @Test
    fun maskDimensionsMatchGridDimensions() {
        val mask = Mask(4, 5)
        assertEquals(4, mask.rows)
        assertEquals(5, mask.columns)
    }

    @Test
    fun maskBitsMatchDimensions() {
        val mask = Mask(2, 3)
        assertEquals(2, mask.bits.size)
        assertEquals(3, mask.bits[0].size)
        assertEquals(3, mask.bits[1].size)
    }

    @Test
    fun maskBitsDefaultToTrue() {
        val mask = Mask(2, 3)
        assertTrue(mask.bits[0].all { true })
        assertTrue(mask.bits[1].all { true })
    }

    @Test
    fun maskBitsCanBeQueriedIndividually() {
        val mask = Mask(2, 2)

        assertTrue(mask.at(0, 0))
        assertFalse(mask.at(-1, 0))
        assertFalse(mask.at(0, -1))
        assertFalse(mask.at(1, 2))
        assertFalse(mask.at(2, 1))
    }

    @Test
    fun maskBitsCanBeUpdatedIndividually() {
        val mask = Mask(2, 2)

        mask.set(0, 0, false)
        assertFalse(mask.at(0, 0))
    }

    @Test
    fun invalidMaskBitsCannotBeUpdated() {
        val mask = Mask(2, 2)

        Assert.assertThrows(IllegalArgumentException::class.java) { mask.set(-1, 0, false) }
        Assert.assertThrows(IllegalArgumentException::class.java) { mask.set(0, 2, true) }
    }

    @Test
    fun masksRememberHowManyBitsAreEnabled() {
        val mask = Mask(2, 2)

        assertEquals(4, mask.count())

        mask.set(0, 0, false)
        assertEquals(3, mask.count())

    }

    @Test
    fun masksCanReturnARandomEnabledBit() {
        val mask = Mask(1, 2)
        mask.set(0, 0, false)

        for (count in 1..50) {
            assertEquals(Pair(0, 1), mask.randomBit(), "Returned disabled bit on iteration $count")
        }

    }

    @Test
    fun maskCanBeConfiguredFromDSL() {
        val mask = Mask.from(listOf("X...", ".X..", "..X."))

        assertEquals(3, mask.rows)
        assertEquals(4, mask.columns)

        assertEquals(mask.bits[0][0], false)
        assertEquals(mask.bits[0][1], true)
        assertEquals(mask.bits[1][1], false)
        assertEquals(mask.bits[2][3], true)
    }
}