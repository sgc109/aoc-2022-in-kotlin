private const val COMMAND_TEMPLATE = "move (\\d+) from (\\d+) to (\\d+)"

fun main() {
    data class Command(
        val cnt: Int,
        val from: Int,
        val to: Int,
        val retained: Boolean = false,
    )

    data class Game(
        val stacks: List<ArrayDeque<Char>>,
        val commands: List<Command>,
    ) {
        fun execute(command: Command) {
            require(stacks[command.from].size >= command.cnt)
            val pops = (1..command.cnt).map {
                stacks[command.from].removeLast()
            }
            if (command.retained) {
                pops.reversed()
            } else {
                pops
            }.forEach {
                stacks[command.to].addLast(it)
            }
        }
    }

    fun initGame(input: String, retained: Boolean = false): Game {
        val (part1, part2) = input.split("\n\n")
        val upperLines = part1.split("\n")
        val commandLines = part2.split("\n")

        val size = upperLines.last().split(" ").filter { it.isNotBlank() }.maxOfOrNull { it.toInt() }!!
        val stacks = List(size) { ArrayDeque<Char>() }

        upperLines.dropLast(1).reversed().forEach { line ->
            var idx = 0
            line.windowed(size = 3, step = 4) { box ->
                "\\[(\\w)]"
                    .toRegex()
                    .matchEntire(box)
                    ?.groups
                    ?.get(1)
                    ?.value
                    ?.let {
                        stacks[idx].addLast(it[0])
                    }
                idx++
            }
        }
        val commands = commandLines.map { line ->
            val res = COMMAND_TEMPLATE.toRegex().matchEntire(line)!!
            val (cnt, from, to) = res.groups.toList().drop(1).mapNotNull {
                it?.value?.toInt()
            }
            Command(cnt, from - 1, to - 1, retained)
        }

        return Game(stacks, commands)
    }

    fun part1(input: String): String {
        val game = initGame(input)
        game.commands.forEach {
            game.execute(it)
        }
        return game.stacks.map { it.last() }.joinToString("")
    }

    fun part2(input: String): String {
        val game = initGame(input, retained = true)
        game.commands.forEach {
            game.execute(it)
        }
        return game.stacks.map { it.last() }.joinToString("")
    }

    val testInput = readText("Day05_test")
    val ans = part2(testInput)
    check(ans == "MCD")

    val input = readText("Day05")
    part1(input).println()
    part2(input).println()
}
