fun main() {
    fun part1(input: String): Long {
        return input.split("\n\n").maxOfOrNull { chunk ->
            chunk.split("\n").sumOf { numStr ->
                numStr.toLong()
            }
        }!!
    }

    fun part2(input: String): Long {
        val sums = input.split("\n\n").map {
            it.split("\n").sumOf { it.toLong() }
        }
        return sums.sortedDescending().subList(0, 3).sum()
    }

    val testInput = readText("Day01_test")
    check(part1(testInput) == 24000L)

    val input = readText("Day01")
    part1(input).println()
    part2(input).println()
}
