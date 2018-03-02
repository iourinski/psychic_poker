package hands

import models.Card

object TwoPair  extends Hand {
  def isThere(cards: List[Card]): Boolean = {
    val filteredCards = cards
      .groupBy(_.faceValue)
      .filter(_._2.length == 2)
    filteredCards.size == 2
  }
  def name(): String = "two-pairs"
}
