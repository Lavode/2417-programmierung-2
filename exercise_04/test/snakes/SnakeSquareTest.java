package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;

import snakes.squares.LadderSquare;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SnakeSquareTest {
	@Test
	public void testLandHereOrGoHomeDefault()
	{
		Square start = mock(Square.class);
		when(start.landHereOrGoHome()).thenReturn(start);

		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(game.getSquare(1)).thenReturn(start);

		SnakeSquare testSquare = new SnakeSquare(-1, game, 2);
		assertEquals(start, testSquare.landHereOrGoHome());
	}

	@Test (expected = AssertionError.class)
	public void positiveTransportFailsContract()
	{
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);

		SnakeSquare testSquare = new SnakeSquare(1, game, 1);
	}

	@Test (expected = AssertionError.class)
	public void transportLeadingOutsideOfBoardFailsContract()
	{
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(false);

		SnakeSquare testSquare = new SnakeSquare(-5, game, 1);
	}
}
