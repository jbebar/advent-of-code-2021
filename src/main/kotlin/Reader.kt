object Reader {

    fun readInputs(fileName: String) = this::class.java.getResourceAsStream(fileName)!!.reader().readLines()

}