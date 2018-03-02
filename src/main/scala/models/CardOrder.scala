package models

object CardOrder {
  val cardOrder = FaceValues.values.zipWithIndex.toMap
  def > (firstCard: FaceValues.Value, secondCard: FaceValues.Value): Boolean =
    cardOrder(firstCard) > cardOrder(secondCard)

  def nextValue(card: Card): Card = {
    if (card.faceValue == FaceValues.Ace) {
      new Card(FaceValues.Two, card.suit)
    } else {
      val cardValue = cardOrder.filter(x => x._2 == cardOrder(card.faceValue) + 1).head._1
      new Card(cardValue, card.suit)
    }
  }
  def sortCards(cards: List[Card]): List[Card] = {
    val res = scala.collection.mutable.ListBuffer[Card]()
    for (faceVal <- cardOrder.toList.sortBy(_._2)) {
      for (card <- cards.filter(_.faceValue == faceVal._1)) {
        res.+=(card)
      }
    }
    res.toList
  }
}
