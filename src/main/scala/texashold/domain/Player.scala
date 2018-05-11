package main.scala.texashold.domain

case class Player(playerId: Int, hand: Seq[Card] = Seq.empty[Card])

object Player {
  def buildPlayers(playerCount: Int): Seq[Player] = (1 to playerCount).map(Player(_))
}
