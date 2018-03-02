package hands

import models.Card

object HighCard extends Hand {
  def isThere(cards: List[Card]): Boolean = {
    !Straight.isThere(cards) && cards.groupBy(_.faceValue).size == cards.size && cards.groupBy(_.suit).size > 1
  }
  def name(): String = "highest-card"
}
