package main.scala.texeshold.domain
import Deck._

case class Table(shownCards: Seq[Card] = Seq.empty[Card]) {

  def dealToTable(deck: Seq[Card], cardsToDeal: Int = 3): Option[(Table, Seq[Card])] = {
    val (newShownCards, newDeck) = deck.dealCards(cardsToDeal)

    Some((this.copy(shownCards = this.shownCards ++ newShownCards), newDeck.discardTopCard))
  }
}
