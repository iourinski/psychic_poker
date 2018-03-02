package hands

import models.Card

object ThreeOfKind extends Hand {
  def isThere(cards: List[Card]): Boolean = {
    cards.groupBy(_.faceValue).exists(_._2.length == 3)
  }
  def name(): String = "three-of-a-kind"
}
