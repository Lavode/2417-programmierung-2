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
	
	@Test
	public void testLandHereOrGoHomeDefault()
	{
		Game game = mock(Game.class);
		Square testSquare;
		Square start = mock(Square.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(game.firstSquare()).thenReturn(start);
		testSquare = new StandardSquare(game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(testSquare, destination);
	}
	
	@Test
	public void testLandHereOrGoHomeOccupiedEnterAndLeave()
	{
		Game game = mock(Game.class);
		Square testSquare;
		Player player = mock(Player.class);
		when(player.position()).thenReturn(1);
		Square start = mock(Square.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(game.firstSquare()).thenReturn(start);
		testSquare = new StandardSquare(game, 1);
		testSquare.enter(player);
		assertTrue(testSquare.isOccupied());
		Square destination = testSquare.landHereOrGoHome();
		assertEquals(start, destination);
		testSquare.leave(player);
		assertTrue(!testSquare.isOccupied());
	}
}
