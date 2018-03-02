package hands

import models.Card

trait Hand {
  def isThere(cards: List[Card]): Boolean
  def name(): String
}
