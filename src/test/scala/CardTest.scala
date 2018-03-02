import app.InputProcessor
import models._
import hands.{FourOfKind, StraightFlush, TwoPair}
import org.scalatest.FunSuite

class CardTest extends FunSuite {
  val processor = new InputProcessor(5, 5)
  val queen: Card = processor.initializeCard("QH")
  val init: String => Card = processor.initializeCard

  test("we can initialize a card") {
    assert(queen.faceValue == FaceValues.Queen && queen.suit == Suits.Hearts)
  }

  test("we dismiss bad cards") {
    try {
      processor.initializeCard("GH")
    } catch {
      case e: Throwable => println(e.getMessage)
        assert(e.isInstanceOf[IllegalArgumentException])

    }
  }

  test("we can get next card") {
    assert(queen.getNextCard().faceValue == FaceValues.King && queen.getNextCard().suit == Suits.Hearts)
  }

  test("queen is senior to jack") {
    val jack = init("JH")
    assert(queen > jack)
  }

  test("card of different suites do not compare (always false)") {
    val jackOfSpades = init("JS")
    assert(!(queen > jackOfSpades))
  }

  test("ace can be below two") {
    val ace = init("AS")
    assert(ace.getNextCard().faceValue == FaceValues.Two)
  }

  test("we can order cards according to their face value") {
    val cards = List("QH", "JS", "5H", "3C", "AD").map(x => init(x))
    val sortedCards = CardOrder.sortCards(cards)
    assert(sortedCards.head.faceValue == FaceValues.Three)
    assert(sortedCards.reverse.head.faceValue == FaceValues.Ace)
  }

  test("can figure out a straight flush") {
    val cards = List("QH", "JS", "5H", "3C", "AD", "2C", "4C", "5C", "AC").map(x => init(x))
    assert(StraightFlush.isThere(cards))
  }

  test("can figure out four of a kind") {
    val cardsWith4Kind = List("QH", "JS", "5H","QS", "QD", "3C", "AD", "QC", "2C", "4C", "5C", "AC")
      .map(x => init(x))
    assert(FourOfKind.isThere(cardsWith4Kind))
  }

  test("iterate through hands") {
    val cards = List("QH", "JS", "5H", "3C", "AD", "2C", "4C", "5C", "AC").map(x => init(x))
    val hands = List(StraightFlush, TwoPair, FourOfKind).map(x => x.isThere(cards))
    assert(hands == List(true, true, false))
  }

  test("can figure out all the hands") {
    val input = List(
      "TH JH QC QD QS QH KH AH 2S 6S",
      "2H 2S 3H 3S 3C 2D 3D 6C 9C TH",
      "2H 2S 3H 3S 3C 2D 9C 3D 6C TH",
      "2H AD 5H AC 7H AH 6H 9H 4H 3C",
      "AC 2D 9C 3S KD 5S 4D KS AS 4C",
      "KS AH 2H 3C 4H KC 2C TC 2D AS",
      "AH 2C 9S AD 3C QH KS JS JD KD",
      "6C 9C 8C 2D 7C 2H TC 4C 9S AH",
      "3D 5S 2H QD TD 6S KH 9H AD QH"
    )

    val res  = input
      .map(x => processor.parseLine(x))
      .map(game => game.getOptimalHand())

    val expected = List(
      "straight-flush",
      "four-of-a-kind",
      "full-house",
      "flush",
      "straight",
      "three-of-a-kind",
      "two-pairs",
      "one-pair",
      "highest-card"
    )
    assert(res == expected)
  }
}
