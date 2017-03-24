package quoridor;

import java.awt.Point;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quoridor.Game;

public class GameTest
{
	private Player player1 = new Player("George", 'g', new Point(1, 1), Player.Target.LEFT);
	private Player player2 = new Player("John", 'j', new Point(3, 3), Player.Target.DOWN);

	private Game game = new Game (5, 5, player1, player2);

	@Test
	public void equalsChecksForIdenticalPlayers() {
		Game game2 = new Game (5, 5, new Player("Michael", 'M', new Point(1, 1), Player.Target.RIGHT), player2);
		assertNotEquals(game, game2);
	}

	@Test
	public void equalsChecksForIdenticalBoardDimensions() {
		Game game2 = new Game (7, 7, player1, player2);
		assertNotEquals(game, game2);
	}

	@Test
	public void equalsChecksForIdenticalTiles() {
		// @TODO
	}

	@Test
	public void equalsReturnsTrueForIdenticalGames() {
		Game game2 = new Game (5, 5, player1, player2);
		assertEquals(game, game2);
	}
}
