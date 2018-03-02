package hands

object HandsOrder {
  val hands = List(
    StraightFlush,
    FourOfKind,
    FullHouse,
    Flush,
    Straight,
    ThreeOfKind,
    TwoPair,
    OnePair,
    HighCard
  )

  def getBestHand(candidates: List[String]): String = {
    val positions = hands.map(_.name()).zipWithIndex.toMap
    candidates
      .map(x => (x, positions.getOrElse(x, -1)))
      .filter(_._2 >= 0)
      .sortBy(_._2)
      .head
      ._1
  }
}
