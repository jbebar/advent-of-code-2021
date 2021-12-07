class Day6A : Day() {

    override fun run(): Int {
        val numberOfDays = 80
        var fishTimers = formatInput()
        for (i in (1..numberOfDays)) {
            val newFishTimers = (1..fishTimers.count { it == 0 }).map {
                8
            }
            fishTimers = fishTimers
                .map {
                    when {
                        it > 0 -> {
                            it - 1
                        }
                        it == 0 -> {
                            6
                        }
                        else -> {
                            throw IllegalStateException("Cannot have negative fish timer")
                        }
                    }
                }.plus(newFishTimers)
        }
        return fishTimers.count()
    }


}

class Day6B : Day() {

    override fun run(): Int {
        val numberOfDays = 256
        var fishTimers = formatInput()
        for (i in (1..numberOfDays)) {
            fishTimers = fishTimers
                .flatMap {
                    when {
                        it > 0 -> {
                            listOf(it - 1)
                        }
                        it == 0 -> {
                            listOf(6, 8)
                        }
                        else -> {
                            throw IllegalStateException("Cannot have negative fish timer")
                        }
                    }
                }
        }
        return fishTimers.count()
    }


}


private fun formatInput() = Reader.readInputs("Day6.txt").first().split(",").map { it.toInt() }
