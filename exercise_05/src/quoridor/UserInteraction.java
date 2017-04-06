package quoridor;

import java.util.Scanner;

/**
 * Class to handle interaction with the user, e.g. asking him from commands.
 */
public class UserInteraction
{
	private Game game;
	private CommandParser parser;

	/**
	 * Create new instance of this class.
	 *
	 * @parma game Game which the user interaction is for.
	 */
	public UserInteraction(Game game) {
		this.game = game;
		this.parser = new CommandParser(game);
	}

	/**
	 * Ask user for next command.
	 *
	 * This will continue to prompt the user, until he entered a
	 * syntactically and semantically correct command.
	 *
	 * @return ICommand user-supplieid command.
	 */
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
				ICommand command = parser.parse(input);
				return command;
			} catch (ParserException | CommandInvalidException e) {
				System.out.println(String.format("Invalid command, please try again: %s", e.getMessage()));
			}

		}

	}
}
