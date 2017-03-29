package quoridor;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.*;

import quoridor.Player;

public class PlayerTest
{	
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
	
	//moveUp() Tests
	@Test
	public void moveUpDefault() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(1, 3), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 2), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		assertFalse(player1.currentPosition().equals(null));
		assertTrue(player1.currentPosition().equals(game.getTile(1, 3)));
		assertTrue(player2.currentPosition().equals(game.getTile(1, 2)));
		
		player2.moveUp();
		
		assertTrue(game.getTile(1, 1).isOccupied());
		assertTrue(player2.currentPosition().equals(game.getTile(1, 1)));
		assertTrue(!game.getTile(1, 2).isOccupied());
	}
	
	@Test (expected = TileOccupiedException.class)
	public void moveUpNextOccupied() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(1, 3), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 2), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player1.moveUp();
		System.out.println(player1.currentPosition().toString());
		System.out.println(player2.currentPosition().toString());
	}
	
	@Test (expected = AssertionError.class)
	public void moveUpRunOutOfField() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(1, 3), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 2), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player2.moveUp();
		player2.moveUp();
	}
	
	//moveDown() Tests
	@Test
	public void moveDownDefault() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(1, 3), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 2), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		assertFalse(player1.currentPosition().equals(null));
		assertTrue(player1.currentPosition().equals(game.getTile(1, 3)));
		assertTrue(player2.currentPosition().equals(game.getTile(1, 2)));
		
		player1.moveDown();
		
		assertTrue(game.getTile(1, 4).isOccupied());
		assertTrue(player1.currentPosition().equals(game.getTile(1, 4)));
		assertTrue(!(game.getTile(1, 3).isOccupied()));
	}
	
	@Test (expected = TileOccupiedException.class)
	public void moveDownNextOccupied() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(1, 3), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 2), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player2.moveDown();
	}
	
	@Test (expected = AssertionError.class)
	public void moveDownRunOutOfField() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(1, 4), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 2), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player1.moveDown();
		player1.moveDown();
	}
	
	//moveRight() Tests
	@Test
	public void moveRightDefault() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(2, 1), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 1), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		assertFalse(player1.currentPosition().equals(null));
		assertTrue(player1.currentPosition().equals(game.getTile(2, 1)));
		assertTrue(player2.currentPosition().equals(game.getTile(1, 1)));
		
		player1.moveRight();
		
		assertTrue(game.getTile(3, 1).isOccupied());
		assertTrue(player1.currentPosition().equals(game.getTile(3, 1)));
		assertTrue(!game.getTile(2, 1).isOccupied());
	}
	
	@Test (expected = TileOccupiedException.class)
	public void moveRightNextOccupied() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(2, 1), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 1), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player2.moveRight();
	}
	
	@Test (expected = AssertionError.class)
	public void moveRightRunOutOfField() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(4, 1), Player.Target.DOWN);
		Player player2 = new Player("Player2", '2', game.getTile(1, 1), Player.Target.DOWN);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player1.moveRight();
		player1.moveRight();
	}
	
	//moveLeft() Tests
	@Test
	public void moveLeftDefault() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(5, 1), Player.Target.DOWN);
		Player player2 = new Player("Player2", '2', game.getTile(4, 1), Player.Target.DOWN);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		assertFalse(player1.currentPosition().equals(null));
		assertTrue(player1.currentPosition().equals(game.getTile(5, 1)));
		assertTrue(player2.currentPosition().equals(game.getTile(4, 1)));
		
		player2.moveLeft();
		
		assertTrue(game.getTile(3, 1).isOccupied());
		assertTrue(player2.currentPosition().equals(game.getTile(3, 1)));
		assertTrue(!game.getTile(4, 1).isOccupied());
	}
	
	@Test (expected = TileOccupiedException.class)
	public void moveLeftNextOccupied() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(5, 1), Player.Target.DOWN);
		Player player2 = new Player("Player2", '2', game.getTile(4, 1), Player.Target.DOWN);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player1.moveLeft();
	}
	
	@Test (expected = AssertionError.class)
	public void moveLeftRunOutOfField() throws TileOccupiedException
	{
		Game game = new Game();
		game.setDimension(5, 5);
		Player player1 = new Player("Player1", '1', game.getTile(2, 1), Player.Target.RIGHT);
		Player player2 = new Player("Player2", '2', game.getTile(1, 2), Player.Target.RIGHT);
		player1.enterGame(game);
		player2.enterGame(game);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		
		player2.moveLeft();
	}
}
