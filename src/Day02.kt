fun main() {
    fun part1(input: List<String>): Int {
        var depth = 0
        var horizontalPos = 0

        input.forEach {
            val split = it.split(" ")
            val word = split[0]
            val num = split[1].toInt()
            when (word){
                "forward" -> horizontalPos += num
                "down" -> depth += num
                "up" -> depth -= num
                else -> {
                    println("uh oh")
                }
            }
        }

        return depth * horizontalPos
    }

    fun part2(input: List<String>): Int {
        var depth = 0
        var horizontalPos = 0
        var aim = 0

        input.forEach {
            val split = it.split(" ")
            val word = split[0]
            val num = split[1].toInt()
            when (word){
                "forward" -> {
                    horizontalPos += num
                    depth += aim * num
                }
                "down" -> aim += num
                "up" -> aim -= num
                else -> {
                    println("uh oh")
                }
            }
        }

        return depth * horizontalPos
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
