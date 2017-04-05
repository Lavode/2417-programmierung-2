package quoridor;

import java.util.Scanner;

public class UserInteraction
{
	private Game game;
	private CommandParser parser;

	public UserInteraction(Game game) {
		this.game = game;
		this.parser = new CommandParser(game);
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
				ICommand command = parser.parse(input);
				return command;
			} catch (ParserException | CommandInvalidException e) {
				System.out.println(String.format("Invalid command, please try again: %s", e.getMessage()));
			}

		}

	}
}
