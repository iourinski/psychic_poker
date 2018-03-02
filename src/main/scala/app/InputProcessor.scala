package app

import models.{Card, FaceValues, Game, Suits}
// we may (theoretically) vary size of the dealt hand and number of cards on the top of the  deck)
class InputProcessor (handSize: Int = 5, deckTopSize: Int = 5) {
  def initializeCard(rawInput: String): Card = {
    val input = rawInput.toUpperCase
    val front = "^[2-9TJQKA]"
    val back = "[CDHS]$"

    require((front + back).r.findFirstIn(input) != None, "You have to use a standard 52-card 4-suit deck!")

    val faceValue = FaceValues.values.filter(front.r.findFirstIn(input).get == _.toString).head
    val suit = Suits.values.filter(back.r.findFirstIn(input).get == _.toString).head
    new Card(faceValue, suit)
  }

  def parseLine(line: String): Game = {
    val cardCodes = line.split("\\s+")
    require(cardCodes.length == (handSize + deckTopSize), s"every line has to contain $handSize + $deckTopSize cards")
    val cards = cardCodes.map(code => initializeCard(code))

    val myHand = cards.slice(0, handSize).toList
    val deckTop = cards.slice(handSize, cards.length).toList
    new Game(myHand, deckTop)
  }

  private def getHandString(hand: List[Card], separator: String = " "): String = {
    hand.map(_.getCode()).mkString(separator)
  }

  def printGameRes(line: String): Unit = {
    val game = parseLine(line)
    val res = game.getOptimalHand()
    println(s"Hand: ${getHandString(game.myHand)} Deck: ${getHandString(game.deckTop)} Best hand: $res")
  }
}
