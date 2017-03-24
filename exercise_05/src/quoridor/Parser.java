package quoridor;

import java.awt.Point;

/**
 * Parses a Quoridor file specification and creates a {@link Game} instance.
 */
public class Parser {
	public static Game parse(String input) {
		Player p1 = new Player("John", 'J', new Point(1, 1), Player.Target.LEFT);
		Player p2 = new Player("George", 'G', new Point(2, 2), Player.Target.DOWN);

		return new Game(7, 7, p1, p2);
	}
}
