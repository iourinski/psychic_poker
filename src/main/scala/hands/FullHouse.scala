package hands

import models.Card

object FullHouse  extends Hand {
  def isThere(cards: List[Card]): Boolean = {
    val groupedCards = cards.groupBy(_.faceValue)
    groupedCards.filter(_._2.length == 3).size == 1 && groupedCards.filter(_._2.length == 2).size == 1
  }
  def name(): String = "full-house"
}
