fun main() {
    fun solve(input: String, len: Int = 4): Int {
        return (0..input.length - len).first {
            input.substring(it, it + len).toSet().size == len
        } + len
    }

    fun part1(input: String): Int {
        return solve(input)
    }

    fun part2(input: String): Int {
        return solve(input, 14)
    }

    val testInput = readLines("Day06_test").first()
    val ans = part1(testInput)
    check(ans == 7)

    val input = readLines("Day06").first()
    part1(input).println()
    part2(input).println()
}
