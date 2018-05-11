package main.scala.texashold.controller

import main.scala.texashold.domain.{Card, Player, Table}
import main.scala.texashold.domain.Deck._

case class Game(players: Seq[Player], deck: Seq[Card], table: Table = Table()) {

  private def dealCards: (Seq[Card], Seq[Player]) = players.foldLeft((deck, Seq.empty[Player])) { (value, plyr) =>
    val (dk, newPlyr) = value
    val (hand, newDk) = dk.dealCards(2)

    (newDk, newPlyr :+ plyr.copy(hand = hand))
  }

  private def buildTable(elmDeck: Seq[Card]) = for {
    (tbl1, dec1) <- table.dealToTable(elmDeck) // flop
    (tbl2, dec2) <- tbl1.dealToTable(dec1, 1) // turn
    result <- tbl2.dealToTable(dec2, 1) // river
  } yield result

  def playRound: Game = {

    val (newCards, newPlayers) = dealCards

   buildTable(newCards.discardTopCard) match {
      case Some((tbl, dk)) => this.copy(newPlayers, dk, tbl)
      case _ => this
    }
  }
}
