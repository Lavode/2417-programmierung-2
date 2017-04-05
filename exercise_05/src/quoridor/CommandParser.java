package quoridor;

import java.awt.Point;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CommandParser
{
	/* TODO: Could not get Pattern.compile("...", Pattern.CASE_INSENSITIVE)
	 * to work, for some reason. */
	private static Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^([udlr])$");
	private static Pattern WALL_COMMAND_PATTERN = Pattern.compile("^wall ([0-9]) ([0-9]) ([0-9]) ([0-9])$");

	private Game game;

	public CommandParser(Game game) {
		this.game = game;
	}

	public ICommand parse(String input) throws ParserException {
		try {
			return parseMoveCommand(input.toLowerCase());
		} catch (ParserException e) {
			return parseWallCommand(input.toLowerCase());
		}
	}

	private ICommand parseMoveCommand(String input) throws ParserException {
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

	private ICommand parseWallCommand(String input) throws ParserException {
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

			return validateWallCommand(new WallCommand(from, to));
		}

		throw new ParserException("Invalid wall command.");
	}

	/* TODO: This should probably be done by the WallCommand class. */
	private WallCommand validateWallCommand(WallCommand cmd) throws ParserException {
		int deltaX = Math.abs(cmd.to().x - cmd.from().x);
		int deltaY = Math.abs(cmd.to().y - cmd.from().y);

		if (deltaX > 1) {
			throw new ParserException("Too big gap on x axis.");
		}
		if (deltaY > 1) {
			throw new ParserException("Too big gap on y axis.");
		}

		if (deltaX + deltaY != 1) {
			throw new ParserException("Coordinates not 1 apart.");
		}

		if (!this.game.isValidPosition(cmd.to().x, cmd.to().y) || !this.game.isValidPosition(cmd.from().x, cmd.from().y)) {
			throw new ParserException("Coordinates outside of game field.");
		}


		return cmd;
	}
}
