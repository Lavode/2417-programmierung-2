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
	public void testMovePlayerAssertion()
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
	public void testMovePlayerOverEnd()
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
	public void testWinnerException()
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
	public void testWinnerDefault()
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
