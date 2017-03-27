package quoridor;

import java.awt.Point;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quoridor.Player;

public class PlayerTest
{
	private Player player = new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT);

	@Test
	public void equalsChecksForIdenticalName() {
		assertNotEquals(player, new Player("John", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalSign() {
		assertNotEquals(player, new Player("George", 'H', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalPosition() {
		assertNotEquals(player, new Player("George", 'G', new Tile(new Point(2, 1)), Player.Target.LEFT));
	}

	@Test
	public void equalsChecksForIdenticalTarget() {
		assertNotEquals(player, new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.DOWN));
	}

	@Test
	public void equalsReturnsTrueForIdenticalPlayers() {
		assertEquals(player, new Player("George", 'G', new Tile(new Point(1, 1)), Player.Target.LEFT));
	}
}
