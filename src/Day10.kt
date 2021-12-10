import java.util.ArrayDeque

fun main() {
    fun part1(input: List<String>): Int {
        val stack = ArrayDeque<Char>()
        val openToClose = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val open = setOf('(', '[', '{', '<')
        val score = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        var sum = 0
        input.forEach { line ->
            stack.clear()

            // This lets us break out of the forEach below, could've just
            // used a normal loop, but I wanted to try this out
            run line@ {
                line.forEach { c ->
                    if(c in open){
                        stack.push(c)
                    }else{
                        //Check closing brace matches
                        if(stack.isNotEmpty()){
                            if(openToClose[stack.pop()] != c){
                                sum += score[c]!!
                                return@line
                            }
                        }else{
                            sum += score[c]!!
                            // This never happens but can't be too safe
                            println("Closing brace with no open")
                            return@line
                        }
                    }
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        val stack = ArrayDeque<Char>()
        val openToClose = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
        val open = setOf('(', '[', '{', '<')
        val score = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
        val scores = mutableListOf<Long>()
        input.forEach { line ->
            stack.clear()
            // This lets us break out of the forEach below, could've just
            // used a normal loop, but I wanted to try this out
            run line@ {
                line.forEach { c ->
                    if(c in open){
                        stack.push(c)
                    }else{
                        //Check closing brace matches
                        if(stack.isNotEmpty()){
                            if(openToClose[stack.pop()] != c){
                                //Bad line
                                return@line
                            }
                        }else{
                            // This never happens but can't be too safe
                            println("Closing brace with no open")
                            return@line
                        }
                    }
                }
                var lineScore = 0L
                while(stack.isNotEmpty()){
                    lineScore = (lineScore * 5) + score[openToClose[stack.pop()]]!!
                }
                scores.add(lineScore)
            }
        }
        scores.sort()
        return scores[scores.size / 2]
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}