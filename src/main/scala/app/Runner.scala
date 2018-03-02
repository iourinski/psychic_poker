package app

object Runner {
  def main(args: Array[String]) {
    val parser = new InputProcessor()
    println("Enter the list of cards separated by a space (single or multiple), empty line ends the script: ")
    val in = Iterator
      .continually(io.StdIn.readLine)
      .takeWhile(
        Option(_).fold(false)(_.nonEmpty)
      )
      .toList
    for (ln <- in) {
        parser.printGameRes(ln)
    }
  }
}

