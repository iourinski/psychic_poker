package models

class Card(val faceValue: FaceValues.Value, val suit: Suits.Value) {

  def getNextCard(): Card = CardOrder.nextValue(this)
  def > (otherCard: Card): Boolean =
    this.suit == otherCard.suit && CardOrder.>(this.faceValue, otherCard.faceValue)
  def getCode(): String = this.faceValue.toString + this.suit.toString
}
