package hands

import models.{Card, CardOrder, FaceValues}

object Straight extends Hand {
  def isThere(cards: List[Card]): Boolean = {
    val values = cards.groupBy(_.faceValue).map(x => x._2.head).toList
    if (values.size < cards.length) {
      return false
    }
    val sortedValues = CardOrder.sortCards(values)

    val sortedCards = if (
      sortedValues.head.faceValue == FaceValues.Two && sortedValues.reverse.head.faceValue == FaceValues.Ace
    ) {
      sortedValues.reverse.head :: sortedValues.slice(0, sortedValues.length - 1)
    } else {
      sortedValues
    }

    for (i <- 0 to sortedCards.length - 2) {
      if (sortedCards(i).getNextCard().faceValue != sortedCards(i + 1).faceValue) {
        return false
      }
    }
    true
  }
  def name(): String = "straight"
}
