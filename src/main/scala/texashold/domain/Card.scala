package main.scala.texashold.domain

import main.scala.texashold.domain.Suit.Suit
import scala.util.Random.shuffle


object Suit extends Enumeration {
  type Suit = Value
  val Diamonds = Value("\u2666")
  val Spades = Value("\u2664")
  val Clubs = Value("\u2663")
  val Hearts = Value("\u2665")
}

case class Card(suit: Suit, value: String)

object Deck {

  implicit class Dealer(cards: Seq[Card]) {

    def dealCards(dealcards: Int): (Seq[Card], Seq[Card]) = {
      (cards.take(dealcards), cards.drop(dealcards))
    }

    def discardTopCard: Seq[Card] = cards.drop(1)
  }

}

case class DeckTool() {
  lazy val buildDeck: Seq[Card] = {
    val values: Seq[String] = (1 to 10).map(_.toString).toSeq ++ Seq("J", "Q", "K")
    val suits: Seq[Card] = Suit.values.map(s => values.map(value => Card(s, value))).reduce(_ ++ _)

    shuffle(suits)
  }
}


