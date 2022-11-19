data class Cell(val row: Int, val column: Int) {
    var west: Cell? = null
    var east: Cell? = null
    var south: Cell? = null
    var north: Cell? = null
    val links: MutableList<Cell> = mutableListOf()

    fun link(cell: Cell) {
        this.links.add(cell)
        cell.links.add(this)
    }

    fun unlink(cell: Cell) {
        this.links.remove(cell)
        cell.links.remove(this)
    }

    fun isLinkedTo(cell: Cell?): Boolean {
        return this.links.contains(cell)
    }

}
