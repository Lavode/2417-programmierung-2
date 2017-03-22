package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class LadderSquareTest {
	@Test
	public void testLandHereOrGoHomeDefault()
	{
		Game game = mock(Game.class);
		Square testSquare;
		Square start = mock(Square.class);
		Square end = mock(Square.class);
		when(game.getSquare(2)).thenReturn(end);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(game.firstSquare()).thenReturn(start);
		when(end.landHereOrGoHome()).thenReturn(end);
		testSquare = new LadderSquare(1,game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(end, destination);
	}
	
	@Test (expected = AssertionError.class)
	public void testNegativTransport()
	{
		Game game = mock(Game.class);
		Square testSquare;
		Square start = mock(Square.class);
		Square end = mock(Square.class);
		when(game.getSquare(2)).thenReturn(end);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(game.firstSquare()).thenReturn(start);
		when(end.landHereOrGoHome()).thenReturn(end);
		testSquare = new LadderSquare(-1,game, 1);
	}
	
	@Test (expected = AssertionError.class)
	public void testLargeTransport()
	{
		Game game = mock(Game.class);
		Square testSquare;
		Square start = mock(Square.class);
		Square end = mock(Square.class);
		when(game.getSquare(2)).thenReturn(end);
		when(game.isValidPosition(anyInt())).thenReturn(false);
		when(game.firstSquare()).thenReturn(start);
		when(end.landHereOrGoHome()).thenReturn(end);
		testSquare = new LadderSquare(5,game, 1);
	}
}
