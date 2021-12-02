fun main() {
    fun part1(input: List<String>): Int {
        return input.map {it.toInt()}.windowed(2).count { it[1] > it[0] }
    }

    fun part2(input: List<String>): Int {
        return input.map {it.toInt()}.windowed(4).count { (it[1] + it[2] + it[3]) > (it[0] + it[1] + it[2]) }
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
