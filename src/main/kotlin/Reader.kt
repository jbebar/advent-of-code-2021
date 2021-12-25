object Reader {

    fun readInputLines(fileName: String) = this::class.java.getResourceAsStream(fileName)!!.reader().readLines()
    fun readInputText(fileName: String) = this::class.java.getResourceAsStream(fileName)!!.reader().readText()

}