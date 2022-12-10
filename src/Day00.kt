fun main() {
    fun part1(input: List<String>): Long {
        return 0L
    }

    fun part2(input: List<String>): Long {
        return 0L
    }

    val testInput = readLines("Day00_test")
    val ans = part1(testInput)
    check(ans == 0L)

    val input = readLines("Day00")
    part1(input).println()
    part2(input).println()
}
