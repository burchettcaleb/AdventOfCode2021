import kotlin.math.max
import kotlin.math.min

fun main() {

    fun gcd(num1: Int, num2: Int): Int {
        var gcd = 1
        var i = 1
        val n1 = if(num1 < 0) -num1 else num1
        val n2 = if(num2 < 0) -num2 else num2
        while(i <= min(n1, n2)){
            if(n1 % i == 0 && n2 % i ==0){
                gcd = i
            }
            i++
        }
        return gcd
    }

    fun part1(input: List<String>): Int {
        val map = mutableMapOf<String, Int>()

        // For each vector
        input.forEach { line ->
            val points = line.split("\\D+".toRegex()).map { it.toInt() }
            var x1 = points[0]
            var y1 = points[1]
            var x2 = points[2]
            var y2 = points[3]

            if(x1 == x2){
                if(y1 > y2){
                    val temp = y2
                    y2 = y1
                    y1 = temp
                }
                for(i in y1 .. y2){
                    val key = "$x1,$i"
                    if(map.containsKey(key)){
                        map[key] = map.getValue(key) + 1
                    }else{
                        map[key] = 1
                    }
                }
            }else if(y1 == y2){
                if(x1 > x2){
                    val temp = x2
                    x2 = x1
                    x1 = temp
                }
                for(i in x1 .. x2){
                    val key = "$i,$y1"
                    if(map.containsKey(key)){
                        map[key] = map.getValue(key) + 1
                    }else{
                        map[key] = 1
                    }
                }

            }
        }
        return map.count { (_, value) -> value > 1 }
    }

    fun part2(input: List<String>): Int {
        val map = mutableMapOf<String, Int>()
        input.forEach { line ->
            val points = line.split("\\D+".toRegex()).map { it.toInt() }
            var x1 = points[0]
            var y1 = points[1]
            var x2 = points[2]
            var y2 = points[3]


            if(x1 == x2){
                if(y1 > y2){
                    val temp = y2
                    y2 = y1
                    y1 = temp
                }
                for(i in y1 .. y2){
                    val key = "$x1,$i"
                    if(map.containsKey(key)){
                        map[key] = map.getValue(key) + 1
                    }else{
                        map[key] = 1
                    }
                }
            }else if(y1 == y2){
                if(x1 > x2){
                    val temp = x2
                    x2 = x1
                    x1 = temp
                }
                for(i in x1 .. x2){
                    val key = "$i,$y1"
                    if(map.containsKey(key)){
                        map[key] = map.getValue(key) + 1
                    }else{
                        map[key] = 1
                    }
                }

            }else {
                val changeX = x2 - x1
                val changeY = y2 - y1
                val gcd = gcd(changeX, changeY)
                val slopeX = changeX / gcd
                val slopeY = changeY / gcd

                var x = x1
                var y = y1
                while (min(x1, x2) <= x && x <= max(x1, x2)) {
                    val key = "$x,$y"
                    if (map.containsKey(key)) {
                        map[key] = map.getValue(key) + 1
                    } else {
                        map[key] = 1
                    }
                    y += slopeY
                    x += slopeX
                }
            }
        }
        return map.count { (_, value) -> value > 1 }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
