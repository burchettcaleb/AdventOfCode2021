fun main() {
    data class Octopus(var energy: Int, var flashed: Boolean)

    fun flash(octopi: List<List<Octopus>>, row: Int, col: Int){
        if(octopi[row][col].energy > 9 && !octopi[row][col].flashed){
            octopi[row][col].flashed = true
            for(otherRow in row - 1..row + 1){
                for(otherCol in col - 1..col + 1){
                    // Skip if out of bounds
                    if(otherRow < 0 || otherCol < 0 || otherRow >= octopi.size || otherCol >= octopi[0].size) continue
                    // Increment other octopus and recurse
                    octopi[otherRow][otherCol].energy++
                    flash(octopi, otherRow, otherCol)

                }
            }
        }
    }


    fun part1(input: List<String>): Int {
        val octopi = mutableListOf<List<Octopus>>()
        input.forEachIndexed { row, line ->
            val rowList = mutableListOf<Octopus>()
            line.forEachIndexed{col, char ->
                rowList.add(col, Octopus(char.toString().toInt(), false))
            }
            octopi.add(row, rowList)
        }


        var flashes = 0

        repeat(100){
            // Increment every octopus
            octopi.forEach { line -> line.forEach{ oct -> oct.energy++ } }

            // Flash
            octopi.forEachIndexed { row, line ->
                line.forEachIndexed { col, octopus ->
                    if(octopus.energy > 9 && !octopus.flashed) flash(octopi, row, col)
                }
            }

            // Set flashed octopi to 0
            octopi.forEachIndexed { row, line ->
                line.forEachIndexed { col, octopus ->
                    if(octopus.flashed){
                        flashes++
                        octopus.energy = 0
                        octopus.flashed = false
                    }
                }
            }

        }
        return flashes

    }

    fun part2(input: List<String>): Int {
        val octopi = mutableListOf<List<Octopus>>()
        input.forEachIndexed { row, line ->
            val rowList = mutableListOf<Octopus>()
            line.forEachIndexed{col, char ->
                rowList.add(col, Octopus(char.toString().toInt(), false))
            }
            octopi.add(row, rowList)
        }


        var step = 0
        var found = false

        while(!found){

            step++

            // Increment every octopus
            octopi.forEach { line -> line.forEach{ oct -> oct.energy++ } }

            // Flash
            octopi.forEachIndexed { row, line ->
                line.forEachIndexed { col, octopus ->
                    if(octopus.energy > 9 && !octopus.flashed) flash(octopi, row, col)
                }
            }

            // See if all flashed
            found = true
            octopi.forEach { row ->
                row.forEach { octopi ->
                    if(!octopi.flashed) found = false
                }
            }

            // Set flashed octopi to 0
            octopi.forEachIndexed { row, line ->
                line.forEachIndexed { col, octopus ->
                    if(octopus.flashed){
                        octopus.energy = 0
                        octopus.flashed = false
                    }
                }
            }
        }
        return step
    }

    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
