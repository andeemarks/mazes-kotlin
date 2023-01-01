class DistanceGrid(rows: Int, columns: Int) : Grid(rows, columns) {

    private var _distances: Distances? = null
    private var _maximumDistance: Int = 0
    var distances: Distances?
        get() = _distances
        set(value) {
            _distances = value
            _maximumDistance = _distances!!.maxDistance().second
        }
}
