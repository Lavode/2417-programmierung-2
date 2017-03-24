import exercise05.Game;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Sample test class containing some notes.
 *
 * TODO: You can remove this class if you don't need it.
 */
public class DemoTest {
	@Test
	public void mockitoTest() {
		// You can/should use mocking (using mockito or with manual mock classes as done with
		// the MockDie class in the previous exercise) to avoid depending on too much
		// other classes.
		Game gameMock = mock(Game.class);
		// ...
	}
}
