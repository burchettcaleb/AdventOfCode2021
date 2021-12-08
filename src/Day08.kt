fun main() {
    fun permutations(): List<String> {
        var values = setOf<Char>('a', 'b', 'c', 'd', 'e', 'f', 'g')
        var output = mutableListOf<String>()

        // I hate this and there's definitely a better way
        values.forEach { a ->
            val aValues = values.toMutableSet()
            aValues.remove(a)
            aValues.forEach{ b ->
                val bValues = aValues.toMutableSet()
                bValues.remove(b)
                bValues.forEach{ c ->
                    val cValues = bValues.toMutableSet()
                    cValues.remove(c)
                    cValues.forEach{ d ->
                        val dValues = cValues.toMutableSet()
                        dValues.remove(d)
                        dValues.forEach{ e ->
                            val eValues = dValues.toMutableSet()
                            eValues.remove(e)
                            eValues.forEach{ f ->
                                val fValues = eValues.toMutableSet()
                                fValues.remove(f)
                                val g = fValues.iterator().next()
                                output.add("" + a + b + c + d + e + f + g)
                            }
                        }
                    }
                }
            }
        }
        return output
    }

    fun decode(order: String, encoded: String): Int{
        var lit = MutableList<Boolean>(7){ false }
        encoded.forEach { c ->
            lit[order.indexOf(c)] = true
        }

        //  0
        //1   2
        //  3
        //4   5
        //  6

        if(lit[0] && lit[1] && lit[2] && !lit[3] && lit[4] && lit[5] && lit[6]){
            return 0
        }
        else if(!lit[0] && !lit[1] && lit[2] && !lit[3] && !lit[4] && lit[5] && !lit[6]){
            return 1
        }
        else if(lit[0] && !lit[1] && lit[2] && lit[3] && lit[4] && !lit[5] && lit[6]){
            return 2
        }
        else if(lit[0] && !lit[1] && lit[2] && lit[3] && !lit[4] && lit[5] && lit[6]){
            return 3
        }
        else if(!lit[0] && lit[1] && lit[2] && lit[3] && !lit[4] && lit[5] && !lit[6]){
            return 4
        }
        else if(lit[0] && lit[1] && !lit[2] && lit[3] && !lit[4] && lit[5] && lit[6]){
            return 5
        }
        else if(lit[0] && lit[1] && !lit[2] && lit[3] && lit[4] && lit[5] && lit[6]){
            return 6
        }
        else if(lit[0] && !lit[1] && lit[2] && !lit[3] && !lit[4] && lit[5] && !lit[6]){
            return 7
        }
        else if(lit[0] && lit[1] && lit[2] && lit[3] && lit[4] && lit[5] && lit[6]){
            return 8
        }
        else if(lit[0] && lit[1] && lit[2] && lit[3] && !lit[4] && lit[5] && lit[6]){
            return 9
        }
        else{
            return -1
        }
    }

    fun part1(input: List<String>): Int {
        return input.map{ line -> line.split("|")[1].split(" ").count { it.length == 2 || it.length == 4 || it.length == 3 || it.length == 7 }}.sum()
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        input.forEach { line ->
            val mixed = line.split(" | ")[0].split(" ")
            val output = line.split(" | ")[1].split(" ")

            var correctOrder = ""
            permutations().forEach{ order ->
                if(correctOrder.isNotEmpty()) return@forEach
                if(mixed.map { decode(order, it) }.none { it == -1 }){
                    correctOrder = order
                }
            }

            var answer = MutableList<Int>(4){-1}

            output.forEachIndexed { index, s -> answer[index] = decode(correctOrder, s) }

            sum += answer.map { it.toString() }.joinToString("").toInt()



        }



        return sum
    }



    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
