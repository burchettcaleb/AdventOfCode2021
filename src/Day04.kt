fun main() {

    data class Board(
        val board:  Array<Array<IntArray>>,
        val processed: Boolean
    )

    fun getBoards(input : List<String>): List<Board> {
        var board = Array(5) { Array(5) { IntArray(2) } }
        val boards = mutableListOf<Board>()
        input.windowed(6, 6).forEachIndexed { index , strings ->
            //ignore index 0 as is always blank
            strings[1].split("\\D+".toRegex()).forEachIndexed { i, s -> board[i][] }
        }


        return boards
    }


    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        println(input.drop(1))
        val boards = getBoards(input.drop(1))




































        return 1
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
//    check(part2(testInput) == 5)

    val input = readInput("Day04")
//    println(part1(input))
//    println(part2(input))
}
