package quoridor;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class UserInteraction
{
	private static Pattern MOVE_COMMAND_PATTERN = Pattern.compile("^([udlr])$");
	private static Pattern WALL_COMMAND_PATTERN = Pattern.compile("^wall ([0-9]) ([0-9])$");

	private Game game;

	public UserInteraction(Game game) {
		this.game = game;
	}

	public ICommand askNextCommand() {
		System.out.println(String.format("%s: Enter your next command. (U, D, L, R, Wall <x> <y>)", this.game.currentPlayer().name()));

		return getCommandFromInput();
	}

	private ICommand getCommandFromInput() {
		Scanner scn = new Scanner(System.in);

		while (true) {
			/* Careful, should not close this scanner, as that'd
			 * close STDIN. :P */
			String input = scn.nextLine();
			try {
				ICommand command = parseCommand(input);
				return command;
			} catch (ParserException e) {
				System.out.println("Your command was invalid, please try again.");
			}

		}

	}

	private ICommand parseCommand(String input) throws ParserException {
		ICommand command;

		command = parseMoveCommand(input);

		if (command != null) {
			return command;
		} else {
			throw new ParserException("Invalid command");
		}
	}

	private MoveCommand parseMoveCommand(String input) {
		Matcher matcher = MOVE_COMMAND_PATTERN.matcher(input);
		if (matcher.matches()) {
			switch(matcher.group(1)) {
				case "u":
					return new MoveCommand(MoveCommand.Direction.UP);
				case "d":
					return new MoveCommand(MoveCommand.Direction.DOWN);
				case "l":
					return new MoveCommand(MoveCommand.Direction.LEFT);
				case "r":
					return new MoveCommand(MoveCommand.Direction.RIGHT);
			}
		}
		return null;
	}


}
