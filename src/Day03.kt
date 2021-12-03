fun main() {
    fun part1(input: List<String>): Int {
        val length = input[0].length
        val counts = IntArray(length){ 0 }

        input.forEach {
            for(i in 0 until length){
                if(it[i].toString().toInt() == 1 ){
                    counts[i]++
                }
                else{
                    counts[i]--
                }
            }
        }

        val gammaStr = CharArray(length){'0'}
        val epsilonStr = CharArray(length){'1'}

        for (i in 0 until length){
            if(counts[i] > 0){
                gammaStr[i] = '1'
                epsilonStr[i] = '0'
            }
        }

        val gamma = Integer.parseInt(gammaStr.joinToString(""), 2)
        val epsilon = Integer.parseInt(epsilonStr.joinToString(""), 2)

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        val length = input[0].length

        var o2 = input.toList()
        var co2 = input.toList()


        for(i in 0 until length){
            if(o2.size > 1){
                val (ones, zeroes) = o2.partition { it[i] == '1' }
                o2 = if(ones.size >= zeroes.size) ones else zeroes
            }
            if(co2.size > 1){
                val (ones, zeroes) = co2.partition { it[i] == '1' }
               co2 = if(ones.size >= zeroes.size) zeroes else ones
            }
        }

        val o2Val = Integer.parseInt(o2[0], 2)
        val co2Val = Integer.parseInt(co2[0], 2)

        return o2Val * co2Val
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
