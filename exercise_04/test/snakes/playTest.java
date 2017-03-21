package snakes;

import java.util.Queue;
import org.junit.Test;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class playTest {
	
	@Before
	public void before()
	{
		Queue<Player> players = mock(Queue.class);
		Player player = mock(Player.class);
		Die die = mock(Die.class);
		Game game = new Game(3, players, 6);
		game.die = die;
		
		Square start = mock(Square.class);
		Square square1 = mock(Square.class);
		Square square2 = mock(Square.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(square1.moveAndLand(anyInt())).thenReturn(square2);
		when(square2.moveAndLand(anyInt())).thenReturn(square2);
		when(start.moveAndLand(anyInt())).thenReturn(square2);
		when(players.peek()).thenReturn(player);
	}
	@Test
	public void testMovePlayer()
	{
		
	}
	
	@Test
	public void xx()
	{
		
	}
	
}
