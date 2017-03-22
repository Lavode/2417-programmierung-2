package snakes;

import java.util.Queue;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class WormholeTest {

	@Test
	public void wormholeTeleportsPlayerToRandomExit()
	{
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);

		List<Square> squareList = new LinkedList<Square>(
				Arrays.asList(
					new WormholeExitSquare(game, 3),
					new WormholeExitSquare(game, 4)
				)
		);
		when(game.wormholeExits()).thenReturn(squareList);

		WormholeEntranceSquare entrance = new WormholeEntranceSquare(game, 2);

		/* Ensure all returned tiles are actually wormhole exits. We
		 * can *not* assert that all exits are returned, as that would
		 * be testing nondeterministic behaviour. */
		for (int i = 0; i < 100; i++) {
			assertTrue(squareList.contains(entrance.landHereOrGoHome()));
		}
	}

	@Test
	public void wormholeDoesNotTeleportPlayerIfNoExitsPresent()
	{
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);

		WormholeEntranceSquare sq = new WormholeEntranceSquare(game, 2);
		assertEquals(sq, sq.landHereOrGoHome());
	}
}
