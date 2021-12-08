fun main() {
    fun part1(input: List<String>): Int {
        return input.map{ line -> line.split("|")[1].split(" ").count { it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7 }}.sum()
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
//    check(part2(testInput) == 5)

    val input = readInput("Day08")
    println(part1(input))
//    println(part2(input))
}
