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

	@Test(expected = TileOccupiedException.class)
	public void moveThrowsTileOccupiedExceptionIfMovingOntoOccupiedTile() throws TileOccupiedException {
		Player player2 = new Player("George", 'G', new Tile(new Point(2, 3)), Player.Target.LEFT);
		this.game.addPlayer(player2);
		player2.enterGame(this.game);

		player2.moveRight();
	}

	@Test
	public void hasFinishedReturnsTrueIfPlayerIsOnFinishLine() throws TileOccupiedException {
		this.player.setTarget(Player.Target.UP);
		assertFalse(this.player.hasFinished());
		this.player.jump(3, 1);
		assertTrue(this.player.hasFinished());

		this.player.setTarget(Player.Target.LEFT);
		assertFalse(this.player.hasFinished());
		this.player.jump(1, 1);
		assertTrue(this.player.hasFinished());

		this.player.setTarget(Player.Target.DOWN);
		assertFalse(this.player.hasFinished());
		this.player.jump(1, 3);
		assertTrue(this.player.hasFinished());

		this.player.setTarget(Player.Target.RIGHT);
		assertFalse(this.player.hasFinished());
		this.player.jump(3, 3);
		assertTrue(this.player.hasFinished());
	}

	@Test
	public void hasFinishedReturnsTrueIfPlayerIsOnTargetTile() throws TileOccupiedException {
		this.player.jump(1, 1);
		this.player.addTargetTile(this.game.getTile(1, 2));
		assertFalse(this.player.hasFinished());
		this.player.jump(1, 2);
		assertTrue(this.player.hasFinished());
	}

	@Test
	public void toStringReturnsUseableRepresentation() {
		assertEquals("<John> [J] @ (3, 3) -> RIGHT", this.player.toString());
	}

	@Test
	public void placeWallPlacesWalls() throws TileOccupiedException {
		this.player.placeWall(new Point(2, 1), new Point(2, 2));
		assertTrue(this.game.getTile(2, 1).hasWall());
		assertTrue(this.game.getTile(2, 2).hasWall());
	}

	@Test(expected = AssertionError.class)
	public void placeWallIfNoWallsAvailableValidatesContract() throws TileOccupiedException {
		this.game.setDimension(10, 10);
		this.player.setAvailableWalls(2);
		this.player.placeWall(new Point(2, 1), new Point(2, 2));
		this.player.placeWall(new Point(3, 1), new Point(3, 2));
		this.player.placeWall(new Point(4, 1), new Point(4, 2));
	}

	@Test
	public void hasGameReturnsWhetherPlayerEnteredGame() {
		Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);

		assertFalse(player.hasGame());
		player.enterGame(this.game);
		assertTrue(player.hasGame());
	}

	@Test
	public void targetTilesContainsTilesOfTarget() {
		List<Tile> expected = new ArrayList<Tile>();
		expected.add(this.game.getTile(3, 1));
		expected.add(this.game.getTile(3, 2));
		expected.add(this.game.getTile(3, 3));

		assertEquals(expected, this.player.targetTiles());
	}

	@Test
	public void targetTilesContainsArbitrarySpecifiedTiles() {
		List<Tile> expected = new ArrayList<Tile>();
		expected.add(this.game.getTile(1, 2));
		expected.add(this.game.getTile(3, 1));

		this.player.setTarget(null);
		this.player.addTargetTile(this.game.getTile(1, 2));
		this.player.addTargetTile(this.game.getTile(3, 1));

		assertEquals(expected, this.player.targetTiles());
	}
}
