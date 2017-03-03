package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SlowDownSquareTest extends SquareTest {
	@Override
	@Before
	public void newGame() {
		initializeGame(15);
		game.setSquare(3, new SlowDownSquare(game, 3));
	}

	@Test
	public void slowDownSquare() {
		game.movePlayer(2); // moves Jack
		assertEquals(3, jack.position());
		assertEquals(1, jill.position());
		game.movePlayer(4); // moves Jack again, since he's on a RollBackSquare
		assertEquals(3, jack.position());
		assertEquals(5, jill.position());
		game.movePlayer(2); // now it'se Jill's turn
		assertEquals(4, jack.position());
		assertEquals(5, jill.position());
	}
}
