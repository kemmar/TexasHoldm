package main.scala.texeshold

import main.scala.texeshold.controller.Game
import main.scala.texeshold.domain.{DeckTool, Player}

import scala.util.Try

object Application extends App {


  Try {
    lazy val players = Player.buildPlayers(args(0).toInt)
    lazy val cards = DeckTool().buildDeck

    val game = Game(players, cards).playRound

    println(s"starting cards #${cards.size}: ${cards}")
    println(s"cards left in deck #${game.deck.size}: ${game.deck}")
    println(s"players Hands: ${game.players}")
    println(s"Table Hands: ${game.table}")
  } recover {
    case _: ArrayIndexOutOfBoundsException => System.err.println(s"Missing first argument number of players [Int]")
    case _: NumberFormatException => System.err.println(s"Invalid argument: ${args(0)} use number")
    case e: Throwable => System.err.println(e)
  }
}
