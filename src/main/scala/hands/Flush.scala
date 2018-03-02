package hands

import models.Card

object Flush extends Hand {
  def isThere(cards: List[Card]): Boolean =
    cards.groupBy(_.suit).exists(_._2.length == 5)
  def name(): String = "flush"
}
