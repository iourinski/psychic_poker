package hands

import models.Card

object FourOfKind  extends Hand {
  def isThere(cards: List[Card]): Boolean = {
    cards.groupBy(_.faceValue).exists(_._2.length == 4)
  }
  def name(): String = "four-of-a-kind"
}
