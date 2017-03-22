package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class playTest {
	
	@Before
	public void before()
	{
		
		/*when(game.isValidPosition(anyInt())).thenReturn(true);
		when(square1.moveAndLand(anyInt())).thenReturn(square2);
		when(square2.moveAndLand(anyInt())).thenReturn(square2);
		when(start.moveAndLand(anyInt())).thenReturn(square2);
		
		Square start = mock(Square.class);
		Square square1 = mock(Square.class);
		Square square2 = mock(Square.class);
		*/
	}
	
	@Test
	public void testPlay()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		IDie die = mock(IDie.class);
		when(die.roll()).thenReturn(1);
		
		game.play(die);
	}
	
	@Test
	public void testMovePlayer1()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		game.movePlayer(1);
		assertTrue(player1.position() == 2);
	}
	
	@Test (expected = AssertionError.class)
	public void testMovePlayer2()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		game.movePlayer(0);
	}
	
	@Test
	public void testMovePlayer3()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		game.movePlayer(5);
		assertTrue(player1.position() == 2);
	}
	
	@Test (expected = Error.class)
	public void testWinner1()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		game.winner();
	}
	
	@Test
	public void testWinner2()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Player winner;
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(4, players, 6);
		game.movePlayer(3);
		try
		{
			winner = game.winner();
		}
		catch(GameNotOverException e)
		{
			winner = null;
		}
		assertEquals(winner, player1);
	}
}
