import kotlin.random.Random

class Mask(val rows: Int, val columns: Int) {
    fun at(row: Int, column: Int): Boolean {
        if (row < 0 || row >= rows) return false
        if (column < 0 || column >= columns) return false

        return bits[row][column]
    }

    fun set(row: Int, column: Int, value: Boolean) {
        require(row in 0 until rows)
        require(column in 0 until columns)

        bits[row][column] = value
    }

    fun count(): Int {
        return bits.flatMap { it.asIterable() }.count { it }
    }

    fun randomBit(): Pair<Int, Int> {
        val row = Random.nextInt(rows)
        val column = Random.nextInt(columns)

        val randomBit = bits[row][column]

        if (randomBit) {
            return Pair(row, column)
        }

        return randomBit()
    }

    val bits: Array<BooleanArray> = Array(rows) { BooleanArray(columns) { true } }
}
