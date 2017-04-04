package quoridor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.*;

import quoridor.Player;

public class PlayerTest
{
	private Game game;
	private Player player;

	@Before
	public void init() {
		this.game = new Game();
		this.game.setDimension(3, 3);
		this.player = new Player("John", 'J', this.game.getTile(3, 3), Player.Target.RIGHT);
		this.game.addPlayer(this.player);
		this.player.enterGame(this.game);
	}

	@Test
	public void equalsChecksForIdenticalName() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("John", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalSign() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("George", 'H', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalPosition() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("George", 'G', new Tile(new Point(2, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalTarget() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertNotEquals(player, new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.DOWN));
	}

	@Test
	public void equalsReturnsTrueForIdenticalPlayers() throws TileOccupiedException{
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);
		assertEquals(player, new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void jumpMovesPlayerToNewPosition() throws TileOccupiedException {
		this.player.jump(2, 2);
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 2));
	}

	@Test
	public void moveUpMovesPlayerUpwards() throws TileOccupiedException {
		this.player.jump(2, 2);
		this.player.moveUp();
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 1));
		assertTrue(this.game.getTile(2, 1).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}

	@Test
	public void moveDownMovesPlayerDownwards() throws TileOccupiedException {
		this.player.jump(1, 1);
		this.player.moveDown();
		assertEquals(this.player.currentPosition(), this.game.getTile(1, 2));
		assertTrue(this.game.getTile(1, 2).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}

	@Test
	public void moveLeftMovesPlayerLeftwards() throws TileOccupiedException {
		this.player.jump(2, 2);
		this.player.moveLeft();
		assertEquals(this.player.currentPosition(), this.game.getTile(1, 2));
		assertTrue(this.game.getTile(1, 2).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}

	@Test
	public void moveRightMovesPlayerRightwards() throws TileOccupiedException {
		this.player.jump(1, 2);
		this.player.moveRight();
		assertEquals(this.player.currentPosition(), this.game.getTile(2, 2));
		assertTrue(this.game.getTile(2, 2).isOccupied());
		assertFalse(this.game.getTile(1, 1).isOccupied());
	}
}
