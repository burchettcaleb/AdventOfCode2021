fun main() {
    data class BingoValue(
        var squareValue: Int,
        var marked: Boolean
    )

    fun getBoards(input : List<String>): MutableList<Array<Array<BingoValue>>> {
        val boards = mutableListOf<Array<Array<BingoValue>>>()
        input.windowed(6, 6).forEachIndexed { _, strings ->
            // Parse each board and add to boards
            val board = Array(5) { Array(5) { BingoValue(0,false) } }

            // Ignore index 0 as it is always the blank line between boards
            strings.drop(1).forEachIndexed { row, string ->
                // Each line
                string.split(" ").filter { it.isNotEmpty() }.map { it.toInt() }.forEachIndexed { column, int ->
                    // Each value
                    board[row][column].squareValue = int
                }
            }

            boards.add(board)
        }

        return boards
    }

    fun hasWon(input : Array<Array<BingoValue>>): Boolean {
        var winner = false

        // Check Rows
        for(row: Int in 0..4){
            var winningRow = true
            for(column: Int in 0..4){
                if(!input[row][column].marked){
                    winningRow = false
                }
            }
            if (winningRow){
                winner = true
            }
        }

        // Check Columns
        for(column: Int in 0..4){
            var winningColumn = true
            for(row: Int in 0..4){
                if(!input[row][column].marked){
                    winningColumn = false
                }
            }
            if (winningColumn){
                winner = true
            }
        }
        return winner
    }

    fun mark(input : Array<Array<BingoValue>>, num : Int) {
        for(row: Int in 0..4){
            for(column: Int in 0..4){
                if(input[row][column].squareValue == num){
                    input[row][column].marked = true
                }
            }
        }
    }

    fun tallyAllNonMarkedSquares(input : Array<Array<BingoValue>>) : Int{
        var sum = 0
        for(row: Int in 0..4){
            for(column: Int in 0..4){
                if(!input[row][column].marked){
                    sum += input[row][column].squareValue
                }
            }
        }
        return sum
    }


    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = getBoards(input.drop(1))

        numbers.forEach { num ->
            boards.forEach { board ->
                mark(board, num)
                if(hasWon(board)){
                    return tallyAllNonMarkedSquares(board) * num
                }
            }
        }

        println("No winner found :(")
        return 0
    }

    fun part2(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = getBoards(input.drop(1))
        val alterableBoards: ArrayList<Array<Array<BingoValue>>> = ArrayList(boards)
        var winningScore = 0

        numbers.forEach { num ->
            boards.forEachIndexed { _, board ->
                mark(board, num)
                if(alterableBoards.size > 0){
                    if(hasWon(board)){
                        alterableBoards.remove(board)
                        if(alterableBoards.size == 0){
                            winningScore = tallyAllNonMarkedSquares(board) * num
                        }
                    }
                }
            }
        }
        return winningScore
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
