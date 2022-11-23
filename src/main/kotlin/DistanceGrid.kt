import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.rendering.TextStyle

class DistanceGrid(rows: Int, columns: Int) : Grid(rows, columns) {
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

        val distance = distances!!.distanceFor(cell) ?: return TextColors.white
        val intensity = (_maximumDistance - distance).toFloat() / _maximumDistance

        return TextColors.rgb(intensity, intensity, 0).bg
    }
}
