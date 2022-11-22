import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

class DistanceGrid(rows: Int, columns: Int) : Grid(rows, columns) {
    private lateinit var _distances: Distances
    private var _maximumDistance: Int = 0
    var distances: Distances
        get() = _distances
        set(value) {
            _distances = value
            _maximumDistance = _distances.maxDistance().second
        }

    fun distanceAsSingleChar(distance: Int): String = distance.toUInt().toString(35)

    override fun cellContentsFor(cell: Cell): String {
        if (distances.forCell(cell) != null) {
            val style = backgroundColourFor(cell)
            return style(distanceAsSingleChar(distances.forCell(cell)!!))
        }

        return super.cellContentsFor(cell)
    }

    override fun backgroundColourFor(cell: Cell): TextStyle {
        val distance = distances.forCell(cell)

        if (distance == null || distance == 0) return TextColors.white

        val intensity = (_maximumDistance - distance).toFloat() / _maximumDistance
//        val dark = (255 * intensity).roundToInt()
//        val bright = 128 + (127 * intensity).roundToInt()

        return TextColors.rgb(intensity, intensity, intensity)
    }
}
