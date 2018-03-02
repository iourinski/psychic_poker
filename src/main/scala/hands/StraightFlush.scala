package hands

import models.{Card, CardOrder, FaceValues}

object StraightFlush extends Hand {
  def isThere(cards: List[Card]): Boolean = {
    val sameSuite = cards.groupBy(_.suit).filter(_._2.length == 5)
    if (sameSuite.toList.length == 0) {
      return false
    }
    val preSortedCards = CardOrder.sortCards(sameSuite.toList.head._2)
    val sortedCards =
      if (preSortedCards.head.faceValue == FaceValues.Two && preSortedCards.reverse.head.faceValue == FaceValues.Ace) {
        preSortedCards.reverse.head :: preSortedCards.slice(0, preSortedCards.length - 1)
      } else {
        preSortedCards
      }
    for (i <- 0 to sortedCards.length - 2){
      if (sortedCards(i).getNextCard().faceValue != sortedCards(i + 1).faceValue){
        return false
      }
    }
    true
  }
  def name(): String = "straight-flush"
}
