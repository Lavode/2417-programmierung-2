package snakes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpeedUpSquareTest extends SquareTest {
	@Override
	@Before
	public void newGame() {
		initializeGame(15);
		game.setSquare(3, new SpeedUpSquare(game, 3));
	}

	public void speedUpSquare() {
		this.newGame();
		this.Test1();
		
		this.newGame();
		this.Test2();
		
		this.newGame();
		this.Test3();
	}
	
	@Test
	public void Test1() {
		game.movePlayer(2); // moves Jack
		assertEquals(3, jack.position());
		assertEquals(1, jill.position());
		game.movePlayer(4); // moves Jill
		assertEquals(3, jack.position());
		assertEquals(5, jill.position());
		// Now it's Jack's turn - due to being on a SpeedUp square, he
		// should move twice this amount.
		game.movePlayer(2);
		assertEquals(7, jack.position());
		assertEquals(5, jill.position());
	}
	
	@Test
	public void Test2() {
		game.movePlayer(2); // moves Jack
		assertEquals(3, jack.position());
		assertEquals(1, jill.position());
		game.movePlayer(4); // moves Jill
		assertEquals(3, jack.position());
		assertEquals(5, jill.position());
		// Now it's Jack's turn - due to being on a SpeedUp square, he
		// should move twice this amount.
		game.movePlayer(6);
		assertEquals(15, jack.position());
		assertEquals(5, jill.position());
	}
	
	@Test
	public void Test3() {
		game.movePlayer(2); // moves Jack
		assertEquals(3, jack.position());
		assertEquals(1, jill.position());
		game.movePlayer(4); // moves Jill
		assertEquals(3, jack.position());
		assertEquals(5, jill.position());
		// Now it's Jack's turn - due to being on a SpeedUp square, he
		// should move twice this amount.
		game.movePlayer(1);
		//jack lands on jills square, so he goes home
		assertEquals(1, jack.position());
		assertEquals(5, jill.position());
	}
}
