fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0

        for(y in input.indices){
            for(x in 0 until input[0].length){
                val height = input[y][x].toString().toInt()
                val above = if(y + 1 < input.size ) input[y + 1][x].toString().toInt() else Int.MAX_VALUE
                val below = if(y - 1 >= 0 ) input[y - 1][x].toString().toInt() else Int.MAX_VALUE
                val left = if(x - 1 >= 0 ) input[y][x - 1].toString().toInt() else Int.MAX_VALUE
                val right = if(x + 1 < input[0].length ) input[y][x + 1].toString().toInt() else Int.MAX_VALUE
                if(height < above && height < below && height < left && height < right){
                    sum += height + 1
                }
            }
        }
        return sum
    }

    fun basinSize(input: List<String>, x: Int, y: Int): Set<String>{
        // Set so we don't count points multiple times
        val points = mutableSetOf<String>()
        points.add("$x,$y")

        val above = if(y + 1 < input.size ) input[y + 1][x].toString().toInt() else -1
        val below = if(y - 1 >= 0 ) input[y - 1][x].toString().toInt() else -1
        val left = if(x - 1 >= 0 ) input[y][x - 1].toString().toInt() else -1
        val right = if(x + 1 < input[0].length ) input[y][x + 1].toString().toInt() else -1

        if(input[y][x].toString().toInt() < above && above < 9){
            points.addAll(basinSize(input, x, y + 1))
        }
        if(input[y][x].toString().toInt() < below && below < 9){
            points.addAll(basinSize(input, x, y - 1))
        }
        if(input[y][x].toString().toInt() < left && left < 9){
            points.addAll(basinSize(input, x - 1, y))
        }
        if(input[y][x].toString().toInt() < right && right < 9){
            points.addAll(basinSize(input, x + 1, y))
        }
        return points
    }

    fun part2(input: List<String>): Int {
        val basinSizes = mutableListOf<Int>()

        for(y in input.indices){
            for(x in 0 until input[0].length){
                val height = input[y][x].toString().toInt()
                val above = if(y + 1 < input.size ) input[y + 1][x].toString().toInt() else Int.MAX_VALUE
                val below = if(y - 1 >= 0 ) input[y - 1][x].toString().toInt() else Int.MAX_VALUE
                val left = if(x - 1 >= 0 ) input[y][x - 1].toString().toInt() else Int.MAX_VALUE
                val right = if(x + 1 < input[0].length ) input[y][x + 1].toString().toInt() else Int.MAX_VALUE
                if(height < above && height < below && height < left && height < right){
                    // For each low point
                    basinSizes.add(basinSize(input, x.toString().toInt(), y.toString().toInt()).size)
                }
            }
        }
        basinSizes.sortDescending()
        return basinSizes[0] * basinSizes[1] * basinSizes[2]
    }

    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
