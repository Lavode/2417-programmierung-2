package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.SwapSquare;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class SwapSquareTest {
	@Test
	public void landHereOrGoHomeSwapsPlayerWithNextPlayer() {
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);

		StandardSquare sq1 = mock(StandardSquare.class);
		Player player1 = mock(Player.class);
		when(player1.position()).thenReturn(1);
		when(game.getSquare(1)).thenReturn(sq1);

		StandardSquare sq2 = mock(StandardSquare.class);
		Player player2 = mock(Player.class);
		when(player2.position()).thenReturn(3);
		when(game.getSquare(3)).thenReturn(sq2);

		when(game.nextPlayer()).thenReturn(player2);

		SwapSquare testSquare = new SwapSquare(game, 2);
		assertEquals(sq2, testSquare.landHereOrGoHome());
	}
}
