import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyle

class DistanceGrid : Grid {
    private val cellColour: TextColors

    constructor(rows: Int, columns: Int, cellColour: TextColors = green) : super(rows, columns) {
        require(
            cellColour in listOf(
                red,
                blue,
                green,
                magenta,
                yellow
            )
        ) { "Color must be one of red, green, blue, yellow or magenta" }

        this.cellColour = cellColour
    }

    private var _distances: Distances? = null
    private var _maximumDistance: Int = 0
    var distances: Distances?
        get() = _distances
        set(value) {
            _distances = value
            _maximumDistance = _distances!!.maxDistance().second
        }

    fun distanceAsSingleChar(distance: Int): String {
        val formattedDistance = distance.toUInt().toString(35)

        if (formattedDistance.length == 1) {
            return " $formattedDistance "
        }

        if (formattedDistance.length == 2) {
            return " $formattedDistance"
        }

        return formattedDistance
    }

    override fun cellContentsFor(cell: Cell): String {
        require(distances != null) { "Distances not yet initialised" }

        distances!!.distanceFor(cell)?.let {
            return distanceAsSingleChar(distances!!.distanceFor(cell)!!)
        }

        return super.cellContentsFor(cell)
    }

    override fun styleFor(cell: Cell): TextStyle {
        require(distances != null) { "Distances not yet initialised" }

        val distance = distances!!.distanceFor(cell) ?: return white
        val intensity = (_maximumDistance - distance).toFloat() / _maximumDistance

        return when (cellColour) {
            red -> TextColors.rgb(intensity, 0, 0).bg
            green -> TextColors.rgb(0, intensity, 0).bg
            blue -> TextColors.rgb(0, 0, intensity).bg
            magenta -> TextColors.rgb(intensity, 0, intensity).bg
            yellow -> TextColors.rgb(intensity, intensity, 0).bg
            else -> throw IllegalArgumentException()
        }
    }
}
