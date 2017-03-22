package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class FirstSquareTest {
	@Test
	public void testLandHereOrGoHome() {
		Game game = mock(Game.class);
		Square testSquare;
		when(game.isValidPosition(anyInt())).thenReturn(true);
		testSquare = new FirstSquare(game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(testSquare, destination);
	}
	
	@Test
	public void testEnterLeave()
	{
		Game game = mock(Game.class);
		Player player = mock(Player.class);
		when(player.position()).thenReturn(1);
		Square testSquare;
		when(game.isValidPosition(anyInt())).thenReturn(true);
		testSquare = new FirstSquare(game, 1);

		testSquare.enter(player);
		assertTrue(testSquare.isOccupied());
		
		testSquare.leave(player);
		assertFalse(testSquare.isOccupied());
	}
}
