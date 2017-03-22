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
	public void landHereOrGoHomePutsPlayerOnThisField() {
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);

		FirstSquare testSquare = new FirstSquare(game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(testSquare, destination);
	}

	@Test
	public void enterAndLeaveSetIsOccupied()
	{
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);

		Player player = mock(Player.class);
		when(player.position()).thenReturn(1);

		FirstSquare testSquare = new FirstSquare(game, 1);

		testSquare.enter(player);
		assertTrue(testSquare.isOccupied());

		testSquare.leave(player);
		assertFalse(testSquare.isOccupied());
	}
}
