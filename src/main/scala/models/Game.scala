package models

import app.InputProcessor
import hands.HandsOrder

import scala.collection.mutable.ListBuffer


class Game (val myHand: List[Card], val deckTop: List[Card]) {

  private def generateSubsets(n: Int): List[List[Card]] = {
    var subSets = List(List[Card]())
    for (i <- 1 to n) {
      subSets = subSets.flatMap(x =>  myHand.map(y => x :+ y))
    }
    subSets
      .map(_.toSet)
      .filter(_.size == n)
      .map(x => x
        .map(_.getCode())
        .toList
        .sortBy(x => x)
        .mkString(",")
      )
      .groupBy(x => x)
      .map(x => x._1
        .split(",")
        .filter(_ != "")
        .map(y  =>  {
          val processor = new InputProcessor(myHand.size, deckTop.size)
          processor.initializeCard(y)
        }).toList
      ).toList
  }

  // depending on how many cards we can see we can vary the number of replaced cards
  private def generateHandsWithReplacements(): List[List[Card]] = {
    val res = ListBuffer[List[Card]](myHand, deckTop)
    for (i <- 1 to deckTop.size - 1) {
      val repls = generateSubsets(i).map(x =>  x ++ deckTop.slice(0, deckTop.size - i))
      res ++= repls
    }
    res.toList
  }

  private def getHandsValue(cards: List[Card]): String = {
    val pokerHands = HandsOrder.hands
    for (handRule <- pokerHands) {
      if (handRule.isThere(cards)){
        return handRule.name()
      }
    }
    "none"
  }

  def getOptimalHand(): String = {
    val handValues = generateHandsWithReplacements()
    val candidates = handValues.map(hand => getHandsValue(hand))
    HandsOrder.getBestHand(candidates)
  }

}
