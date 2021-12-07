import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        val fishes =  input[0].split(",").map { it.toInt() }.toMutableList()

        repeat(80){
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

    fun part2(input: List<String>): Long {
        val fishes = input[0].split(",").map { it.toInt()}.toMutableList()
        val numbers = List(9){it}.map { num -> fishes.count{it == num}.toLong() }.toMutableList()

        repeat(256){
            Collections.rotate(numbers, -1)
            numbers[6] += numbers[8]
        }
        return numbers.fold(0L){sum, num -> sum + num}
    }

    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26_984_457_539)


    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
