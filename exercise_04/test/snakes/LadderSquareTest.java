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
	public void ladderSquareMovesPlayerToEndOfLadder()
	{
		Square end = mock(Square.class);
		when(end.landHereOrGoHome()).thenReturn(end);

		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		when(game.getSquare(2)).thenReturn(end);

		LadderSquare testSquare = new LadderSquare(1, game, 1);

		Square destination = testSquare.landHereOrGoHome();
		assertEquals(end, destination);
	}

	@Test (expected = AssertionError.class)
	public void ladderSquarerWithNegativeTransportFailsContract()
	{
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(true);
		LadderSquare testSquare = new LadderSquare(-1, game, 1);
	}

	@Test (expected = AssertionError.class)
	public void ladderSquareWithTransportLeadingOutsideOfBoardFailsContract()
	{
		Game game = mock(Game.class);
		when(game.isValidPosition(anyInt())).thenReturn(false);
		LadderSquare testSquare = new LadderSquare(5, game, 1);
	}
}
