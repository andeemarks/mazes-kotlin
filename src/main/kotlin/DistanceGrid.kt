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

}
