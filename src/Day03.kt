fun main() {
    fun getPriority(ch: Char): Long =
        if (ch.isLowerCase()) {
            ch - 'a' + 1
        } else {
            ch - 'A' + 1 + 26
        }.toLong()

    fun part1(input: List<String>): Long {
        return input.sumOf { line ->
            val halfLen = line.length / 2
            val left = line.take(halfLen).toSet()
            val right = line.takeLast(halfLen).toSet()

            left.filter {
                right.contains(it)
            }.sumOf {
                getPriority(it)
            }
        }
    }

    fun findBadgePrior(threeBags: List<String>): Long {
        require(threeBags.size == 3)

        val sets = threeBags.map { it.toSet() }

        return sets.reduce { a, b ->
            a.filter { b.contains(it) }
                .toSet()
        }.also {
            require(it.size == 1)
        }.sumOf {
            getPriority(it)
        }
    }

    fun part2(input: List<String>): Long {
        return input.windowed(size = 3, step = 3).sumOf {
            findBadgePrior(it)
        }
    }

    val testInput = readLines("Day03_test")
    val ans = part2(testInput)
    check(ans == 70L)

    val input = readLines("Day03")
    part1(input).println()
    part2(input).println()
}
