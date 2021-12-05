fun main() {



    fun part1(input: List<String>): Int {
        var map = mutableMapOf<String, Int>()

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
        var map = mutableMapOf<String, Int>()
        input.forEach { line ->
            val points = line.split("\\D+".toRegex()).map { it.toInt() }
            var x1 = points[0]
            var y1 = points[1]
            var x2 = points[2]
            var y2 = points[3]

            var slopex = x2 - x1
            var slopey = y2 - y1








        }
        return 1
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
//    check(part2(testInput) == 5)

    val input = readInput("Day05")
    println(part1(input))
//    println(part2(input))
}
