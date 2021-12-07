fun main() {
    fun part1(input: List<String>): Int {
        var locations = input[0].split(",").map{it.toInt()}

        var bestLocation = locations.minOrNull()!!
        var leastFuel = Int.MAX_VALUE;
        for(i in locations.minOrNull()!! ..locations.maxOrNull()!!){
            var fuel = 0;
            locations.forEach { crab ->
                fuel += if(i - crab > 0) i - crab else crab - i
            }
            if(leastFuel > fuel){
                leastFuel = fuel
                bestLocation = i
            }

        }
        return leastFuel
    }

    fun part2(input: List<String>): Int {
        var locations = input[0].split(",").map{it.toInt()}

        var bestLocation = locations.minOrNull()!!
        var leastFuel = Int.MAX_VALUE;
        for(i in locations.minOrNull()!! ..locations.maxOrNull()!!){
            var fuel = 0;
            locations.forEach { crab ->
                val steps = if(i - crab > 0) i - crab else crab - i
                fuel += (steps * (steps + 1)) / 2
            }
            if(leastFuel > fuel){
                leastFuel = fuel
                bestLocation = i
            }
        }
        return leastFuel
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
