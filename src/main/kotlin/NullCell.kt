import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

class NullCell : Cell(-1, -1) {

    override fun formatCell(style: TextStyle?, contents: String): Triple<String, String, String> {
        val cellStyle = TextColors.black.bg

        return Triple(cellStyle("     "), cellStyle("     "), cellStyle("     "))
    }
}
