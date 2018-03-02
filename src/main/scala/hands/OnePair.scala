package hands

import models.Card

object OnePair extends Hand {
  def isThere(cards: List[Card]): Boolean = cards.groupBy(_.faceValue).count(_._2.size == 2) == 1
  def name(): String = "one-pair"
}
