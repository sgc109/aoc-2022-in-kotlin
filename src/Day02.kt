fun main() {
    val fightScore = mapOf(
        "A" to mapOf(
            "X" to 3,
            "Y" to 6,
            "Z" to 0,
        ),
        "B" to mapOf(
            "X" to 0,
            "Y" to 3,
            "Z" to 6,
        ),
        "C" to mapOf(
            "X" to 6,
            "Y" to 0,
            "Z" to 3,
        )
    )
    val singleScore = mapOf("X" to 1, "Y" to 2, "Z" to 3)

    val trans = mapOf(
        "A" to mapOf(
            "X" to "Z",
            "Y" to "X",
            "Z" to "Y",
        ),
        "B" to mapOf(
            "X" to "X",
            "Y" to "Y",
            "Z" to "Z",
        ),
        "C" to mapOf(
            "X" to "Y",
            "Y" to "Z",
            "Z" to "X",
        )
    )

    fun calcScore(you: String, me: String) =
        fightScore[you]!![me]!! + singleScore[me]!!

    fun part1(input: List<String>): Long {
        return input.sumOf {
            val (you, me) = it.split(" ")
            calcScore(you, me)
        }.toLong()
    }

    fun part2(input: List<String>): Long {
        return input.sumOf {
            val (you, me) = it.split(" ")
            calcScore(you, trans[you]!![me]!!)
        }.toLong()
    }

    val testInput = readLines("Day02_test")
    check(part1(testInput) == 15L)

    val input = readLines("Day02")
    part1(input).println()
    part2(input).println()
}
