package app

object Runner {
  // at the moment the script can only parse poker games either from a file of interactively from command line
  // this can be easily extended

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

