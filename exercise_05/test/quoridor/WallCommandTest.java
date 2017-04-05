package quoridor;

import java.awt.Point;

import static org.junit.Assert.*;

import org.junit.*;

import quoridor.Player;

public class WallCommandTest
{
	@Test
	public void equalsChecksForEqualFrom() {
		assertEquals(
				new WallCommand(new Point(1, 1), new Point(2, 2)),
				new WallCommand(new Point(1, 1), new Point(2, 2))
		);

		assertNotEquals(
				new WallCommand(new Point(1, 1), new Point(2, 2)),
				new WallCommand(new Point(1, 2), new Point(2, 2))
		);
	}

	@Test
	public void equalsChecksForIdenticalTo() {
		assertNotEquals(
				new WallCommand(new Point(1, 1), new Point(2, 2)),
				new WallCommand(new Point(1, 1), new Point(2, 3))
		);
	}
}
