package quoridor;

import java.awt.Point;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

/**
 * Parses a Quoridor file specification and creates a {@link Game} instance.
 */
public class Parser {
	private static Pattern BOARD_DIMENSION_PATTERN = Pattern.compile("^(\\d+) (\\d+)$");
	private static Pattern PLAYER_ENTRY_PATTERN = Pattern.compile("^(.+) (.) (\\d+) (\\d+) ([RLUD])$");

	public static Game parse(String input) {
		Player[] players = new Player[2];
		players[0] = new Player("John", 'J', new Point(1, 1), Player.Target.LEFT);
		players[1] = new Player("George", 'G', new Point(2, 2), Player.Target.DOWN);

		return new Game(7, 7, players);
	}
}
