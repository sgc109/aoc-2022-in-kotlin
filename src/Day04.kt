fun main() {
    fun isIncluded(p1: List<Int>, p2: List<Int>): Boolean {
        return (p1[0] <= p2[0] && p1[1] >= p2[1]) ||
                (p1[0] >= p2[0] && p1[1] <= p2[1])
    }

    fun isOverlapped(p1: List<Int>, p2: List<Int>): Boolean {
        return !(p1[1] < p2[0] || p2[1] < p1[0])
    }

    fun toPairs(line: String) =
        line.split(",").map { chunk ->
            chunk.split("-").map { it.toInt() }
        }

    fun part1(input: List<String>): Int {
        return input.map { line ->
            val pairs = toPairs(line)
            if (isIncluded(pairs[0], pairs[1])) {
                1
            } else {
                0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { line ->
            val pairs = toPairs(line)
            if (isOverlapped(pairs[0], pairs[1])) {
                1
            } else {
                0
            }
        }.sum()
    }

    val testInput = readLines("Day04_test")
    val ans = part1(testInput)
    println(ans)
    check(ans == 2)

    val input = readLines("Day04")
    part1(input).println()
    part2(input).println()
}
