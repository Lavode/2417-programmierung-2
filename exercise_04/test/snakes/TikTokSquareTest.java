package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.TikTokSquare;
import snakes.squares.StandardSquare;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class TikTokSquareTest {
	@Test
	public void landHereOrGoHomeAlternatesBetweenTwoExists() {
		StandardSquare dest1 = mock(StandardSquare.class);
		when(dest1.landHereOrGoHome()).thenReturn(dest1);
		StandardSquare dest2 = mock(StandardSquare.class);
		when(dest2.landHereOrGoHome()).thenReturn(dest2);

		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(game.getSquare(2)).thenReturn(dest1);
		when(game.getSquare(3)).thenReturn(dest2);

		TikTokSquare sq = new TikTokSquare(game, 1, 2, 3);
		assertEquals(dest1, sq.landHereOrGoHome());
		assertEquals(dest2, sq.landHereOrGoHome());
		assertEquals(dest1, sq.landHereOrGoHome());
	}
}
