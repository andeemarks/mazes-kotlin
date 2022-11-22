import com.github.ajalt.mordant.rendering.TextColors.brightMagenta
import com.github.ajalt.mordant.rendering.TextColors.brightWhite
import com.github.ajalt.mordant.terminal.Terminal
import kotlin.test.Test

class SidewinderDemo {
    @Test
    fun manualTest() {
        val tree = Sidewinder()
        val grid = tree.on(Grid(10, 10))

        val t = Terminal()
        t.println((brightWhite on brightMagenta)(grid.toString()))
    }
}