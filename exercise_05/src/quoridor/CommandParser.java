package quoridor;

import java.awt.Point;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CommandParser
{
	private static Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^([udlr])$");
	private static Pattern WALL_COMMAND_PATTERN = Pattern.compile("^wall ([0-9]) ([0-9]) ([0-9]) ([0-9])$");

	public static ICommand parse(String input) throws ParserException {
		try {
			return parseMoveCommand(input);
		} catch (ParserException e) {
			// No-op
		}

		try {
			return parseWallCommand(input);
		} catch (ParserException e) {
			// No-op
		}

		throw new ParserException("Not a valid command.");
	}

	private static ICommand parseMoveCommand(String input) throws ParserException {
		Matcher matcher = MOVE_COMMAND_PATTERN.matcher(input);
		if (matcher.matches()) {
			MoveCommand.Direction direction;
			switch (matcher.group(1)) {
				case "u":
					direction = MoveCommand.Direction.UP;
					break;
				case "d":
					direction = MoveCommand.Direction.DOWN;
					break;
				case "l":
					direction = MoveCommand.Direction.LEFT;
					break;
				case "r":
					direction = MoveCommand.Direction.RIGHT;
					break;
				default:
					throw new ParserException("Invalid move direction.");
			}

			return new MoveCommand(direction);
		} else {
			throw new ParserException("Invalid move command.");
		}
	}

	private static ICommand parseWallCommand(String input) throws ParserException {
		Matcher matcher = WALL_COMMAND_PATTERN.matcher(input);

		if (matcher.matches()) {
			Point from = new Point(
					Integer.parseInt(matcher.group(1)),
					Integer.parseInt(matcher.group(2))
			);

			Point to = new Point(
					Integer.parseInt(matcher.group(3)),
					Integer.parseInt(matcher.group(4))
			);

			return new WallCommand(from, to);
		}

		throw new ParserException("Invalid wall command.");
	}
}
