import kotlin.random.Random

class Sidewinder {
    fun on(grid: Grid): Grid {
        grid.eachRow(::initialiseRow)

        return grid
    }

    private fun initialiseRow(row: List<Cell>) {
        val run = mutableListOf<Cell>()
        row.forEach { cell ->
            run.add(cell)

            val atEasternBoundary = cell.east == null
            val atNorthernBoundary = cell.north == null
            val shouldCloseOut = atEasternBoundary || (!atNorthernBoundary && Random.nextInt(2) == 0)

            if (shouldCloseOut) {
                val member = run.random()
                member.north?.let {member.link(member.north!!)}

                run.clear()
            } else {
                cell.link(cell.east!!)
            }
        }
    }

}
