fun main(args: Array<String>) {
    val result = Day::class.sealedSubclasses.first {
        it.simpleName == args[0]
    }.constructors.first().call().run()
    println("Result ${args[0]} : $result")
}

sealed class Day {

    abstract fun run(): Long

}