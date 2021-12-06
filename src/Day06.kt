import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var fishes =  input[0].split(",").map { it.toInt() }.toMutableList()


        for(i in 1..80){

            var newFishCount = 0
            for(i in 0 until fishes.size){
                if(fishes[i] > 0){
                    fishes[i]--
                }else if(fishes[i] == 0){
                    fishes[i] = 6
                    newFishCount++
                }else{
                    println("something bad happened")
                }
            }
            fishes.addAll(List(newFishCount) { 8 })
        }
        return fishes.size
    }

    // Ignore this attempt at being clever
//    fun fishFromStartFish(input: Int): Int {
//        var fishes =  listOf<Int>(input).toMutableList()
//
//        for(i in 1..256){
//
//            var newFishCount = 0
//            for(i in 0 until fishes.size){
//                if(fishes[i] > 0){
//                    fishes[i]--
//                }else if(fishes[i] == 0){
//                    fishes[i] = 6
//                    newFishCount++
//                }else{
//                    println("something bad happened")
//                }
//            }
//            fishes.addAll(List(newFishCount) { 8 })
//        }
//        println(fishes.size)
//        return fishes.size
//    }

    fun part2(input: List<String>): Long {
        var fishes = input[0].split(",").map { it.toInt()}.toMutableList()

        var zeroes = fishes.count { it == 0}.toLong()
        var ones = fishes.count { it == 1}.toLong()
        var twos = fishes.count { it == 2}.toLong()
        var threes = fishes.count { it == 3}.toLong()
        var fours = fishes.count { it == 4}.toLong()
        var fives = fishes.count { it == 5}.toLong()
        var sixes = fishes.count { it == 6}.toLong()
        var sevens = fishes.count { it == 7}.toLong()
        var eights = fishes.count { it == 8}.toLong()



        for(i in 1..256){
            val newFish = zeroes
            zeroes = ones
            ones = twos
            twos = threes
            threes = fours
            fours = fives
            fives = sixes
            sixes = newFish + sevens
            sevens = eights
            eights = newFish
        }

        return zeroes + ones + twos + threes + fours + fives + sixes + sevens + eights
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26_984_457_539)


    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
