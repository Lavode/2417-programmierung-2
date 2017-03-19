package snakes;

import org.junit.Test;
import snakes.squares.Square;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StandardSquareTest {
	@Test
	public void testMoveAndLandOnly() {
		Game game = mock(Game.class);
		Square testSquare;
		Square start, stop;
		when(game.isValidPosition(anyInt())).thenReturn(true);
		testSquare = new StandardSquare(game, 1);
		start = mock(Square.class);
		stop = mock(Square.class);

		when(game.findSquare(1, 2)).thenReturn(start);
		when(start.landHereOrGoHome()).thenReturn(stop);

		Square destination = testSquare.moveAndLand(2);
		assertEquals(stop, destination);
	}
}
