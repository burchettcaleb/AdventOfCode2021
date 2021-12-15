import kotlin.math.min

fun main() {
    class Point(val weight: Int, var work: Int?, val row: Int, val col: Int)

    fun updateSurroundingPoints(points: MutableList<MutableList<Point>>, point: Point, map: MutableMap<Int, MutableSet<Point>>){
        val row = point.row
        val col = point.col
        val pointsToAdd = mutableListOf<Point>()

        // For each neighboring point, if it exists
        if(row - 1 >= 0){
            val left = points[row - 1][col]
            left.work = if(left.work == null) point.work!! + left.weight else min(point.work!! + left.weight, left.work!!)
            pointsToAdd.add(left)
        }
        if(row + 1 < points.size){
            val right = points[row + 1][col]
            right.work = if(right.work == null) point.work!! + right.weight else min(point.work!! + right.weight, right.work!!)
            pointsToAdd.add(right)
        }
        if(col - 1 >= 0){
            val below = points[row][col - 1]
            below.work = if(below.work == null) point.work!! + below.weight else min(point.work!! + below.weight, below.work!!)
            pointsToAdd.add(below)
        }
        if(col + 1 < points[0].size){
            val above = points[row][col + 1]
            above.work = if(above.work == null) point.work!! + above.weight else min(point.work!! + above.weight, above.work!!)
            pointsToAdd.add(above)
        }

        pointsToAdd.forEach { pointToAdd ->
            if(map.containsKey(pointToAdd.work)){
                map[pointToAdd.work]!!.add(pointToAdd)
            }else{
                map[pointToAdd.work!!] = mutableSetOf(pointToAdd)
            }
        }
    }

    fun calculateWorkValues(points: MutableList<MutableList<Point>>){
        val map = mutableMapOf<Int, MutableSet<Point>>()
        points[0][0].work = 0
        map[0] = mutableSetOf(points[0][0])

        var totalWork = 0
        while(points[points.lastIndex][points[0].lastIndex].work == null){
            if(!map.containsKey(totalWork++)) continue
            map[totalWork - 1]!!.forEach{ point ->
                updateSurroundingPoints(points, point, map)
            }
        }
    }

    fun part1(input: List<String>): Int {
        val points = mutableListOf<MutableList<Point>>()
        // Read in points
        input.forEachIndexed{ row, line ->
            points.add(mutableListOf())
            line.forEachIndexed { col, char ->
                points[row].add(Point(char.toString().toInt(), null, row, col))
            }
        }

        calculateWorkValues(points)

        return points[points.lastIndex][points[0].lastIndex].work!!
    }

    fun part2(input: List<String>): Int {
        val points = MutableList(input.size * 5){MutableList(input[0].length * 5){ Point(0,0,0,0)} }

        // Read in points
        input.forEachIndexed{ row, line ->
            line.forEachIndexed { col, char ->
                var weight = char.toString().toInt()
                for(rowOffset in 0 .. points.lastIndex step input.size){
                    var finalWeight = weight
                    for(colOffset in 0..points[0].lastIndex step input[0].length){
                        points[row + rowOffset][col + colOffset] = Point(finalWeight, null, row + rowOffset, col + colOffset)
                        finalWeight++
                        if(finalWeight > 9){
                            finalWeight = 1
                        }
                    }
                    weight++
                    if(weight > 9){
                        weight = 1
                    }
                }
            }
        }

        calculateWorkValues(points)

        return points[points.lastIndex][points[0].lastIndex].work!!
    }

    val testInput = readInput("Day15_test")
    check(part1(testInput) == 40)
    check(part2(testInput) == 315)

    val input = readInput("Day15")
    println(part1(input))
    println(part2(input))
}
