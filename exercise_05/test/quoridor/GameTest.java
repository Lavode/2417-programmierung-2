package quoridor;

import java.awt.Point;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import quoridor.Game;

public class GameTest
{
	private Player player1 = new Player("George", 'g', new Point(1, 1), Player.Target.LEFT);
	private Player player2 = new Player("John", 'j', new Point(3, 3), Player.Target.DOWN);
	private Player[] players;

	private Game game;

	@Before
	public void init() {
		this.players = new Player[2];
		players[0] = player1;
		players[1] = player2;

		this.game = new Game(5, 5, this.players);
	}

	@Test
	public void equalsChecksForIdenticalPlayers() {
		Player[] otherPlayers = new Player[2];
		otherPlayers[0] = new Player("Michael", 'M', new Point(1, 1), Player.Target.RIGHT);
		otherPlayers[1] = player1;

		Game game2 = new Game (5, 5, otherPlayers);
		assertNotEquals(game, game2);
	}

	@Test
	public void equalsChecksForIdenticalBoardDimensions() {
		Game game2 = new Game (7, 7, players);
		assertNotEquals(game, game2);
	}

	@Test
	public void equalsChecksForIdenticalTiles() {
		// @TODO
	}

	@Test
	public void equalsReturnsTrueForIdenticalGames() {
		Game game2 = new Game (5, 5, players);
		assertEquals(game, game2);
	}
}
