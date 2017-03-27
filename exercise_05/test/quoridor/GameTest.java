package quoridor;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import quoridor.Game;

public class GameTest
{
	private Player player1 = new Player("George", 'g', new Tile(new Point(1, 1)), Player.Target.LEFT);
	private Player player2 = new Player("John", 'j',   new Tile(new Point(3, 3)), Player.Target.DOWN);
	private List<Player> players = new ArrayList<Player>(Arrays.asList(player1, player2));

	private Game game;

	@Before
	public void init() {
		this.players = new ArrayList<Player>(
				Arrays.asList(player1, player2)
		);

		this.game = new Game();
		this.game.setDimension(5, 7);
		this.game.addPlayer(player1);
		this.game.addPlayer(player2);
	}

	@Test
	public void getTileReturnsTile() {
		assertTrue(game.getTile(1, 3) instanceof Tile);
	}

	@Test
	public void getTileIsOneIndexed() {
		/* If it's 0-indexed. one of these will raise an
		 * IndexOutOfBoundsException */
		assertTrue(game.getTile(1, 1) instanceof Tile);
		assertTrue(game.getTile(5, 7) instanceof Tile);
	}

	@Test
	public void setDimensionSetsDimensions() {
		assertEquals(5, game.width());
		assertEquals(7, game.height());
	}

	@Test
	public void setDimensionInitialisesBoardWithTiles() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				assertTrue(this.game.tiles().get(i).get(j) instanceof Tile);
			}
		}
	}

	@Test
	public void equalsChecksForIdenticalPlayers() {
		List<Player> otherPlayers = new ArrayList<Player>(
				Arrays.asList(
					new Player("Michael", 'M', new Tile(new Point(1, 1)), Player.Target.RIGHT), 
					player1
				)
		);

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
		Game game2 = new Game(5, 7, players);

		assertEquals(game, game2);
	}
}
