package quoridor;

import java.awt.Point;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Parses a Quoridor file specification and creates a {@link Game} instance.
 */
public class Parser {
	private static Pattern BOARD_DIMENSION_PATTERN = Pattern.compile("^(\\d+) (\\d+)$");
	private static Pattern PLAYER_ENTRY_PATTERN = Pattern.compile("^(.+) (.) (\\d+) (\\d+) ([RLUD])$");

	public static Game parse(String input) throws ParserException {
		Scanner scanner = new Scanner(input);

		if (!scanner.hasNextLine()) {
			throw new ParserException("Not enough lines in board definition!");
		}
		int[] dimension = parseBoardDimension(scanner.nextLine());

		List<Player> players = new ArrayList<Player>();
		while (scanner.hasNextLine()) {
			players.add(parsePlayer(scanner.nextLine()));
		}
		if (players.size() < 2) {
			throw new ParserException("Not enough players defined.");
		}

		return new Game(dimension[0], dimension[1], players.toArray(new Player[0]));
	}

	private static int[] parseBoardDimension(String input) throws ParserException {
		Matcher matcher = BOARD_DIMENSION_PATTERN.matcher(input);
		if (matcher.matches()) {
			int[] dimension = new int[2];
			dimension[0] = Integer.parseInt(matcher.group(1));
			dimension[1] = Integer.parseInt(matcher.group(2));

			return dimension;
		} else {
			throw new ParserException(String.format("Invalid board dimensions: %s", input));
		}
	}

	private static Player parsePlayer(String input) throws ParserException {
		Matcher matcher = PLAYER_ENTRY_PATTERN.matcher(input);
		if (matcher.matches()) {
			Player.Target target;
			switch (matcher.group(5)) {
				case "R":
					target = Player.Target.RIGHT;
					break;
				case "L":
					target = Player.Target.LEFT;
					break;
				case "U":
					target = Player.Target.UP;
					break;
				case "D":
					target = Player.Target.DOWN;
					break;
				default:
					throw new ParserException(String.format("Invalid target for player: %s", input));
			}
			return new Player(
					matcher.group(1),
					matcher.group(2).charAt(0),
					new Point(
						Integer.parseInt(matcher.group(3)),
						Integer.parseInt(matcher.group(4))
					),
					target
			);
		} else {
			throw new ParserException(String.format("Invalid player entry: %s", input));
		}
	}
}
