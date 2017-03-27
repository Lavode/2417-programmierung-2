package quoridor;

import java.awt.Point;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Parses a Quoridor file specification and creates a {@link Game} instance.
 */
public class Parser {
	private static Pattern BOARD_DIMENSION_PATTERN = Pattern.compile("^(\\d+) (\\d+)$");
	private static Pattern PLAYER_ENTRY_PATTERN = Pattern.compile("^(.+) (.) (\\d+) (\\d+) (\\w)$");

	private Game game;

	public Parser() {
		this.game = new Game();
	}

	public Game parseFromFile(String path) throws ParserException, IOException {
		String input = new String(Files.readAllBytes(Paths.get(path)));
		return parse(input);
	}

	public Game parse(String input) throws ParserException {
		Scanner scanner = new Scanner(input);

		if (!scanner.hasNextLine()) {
			scanner.close();
			throw new ParserException("Not enough lines in board definition!");
		}
		parseBoardDimension(scanner.nextLine());
		parsePlayers(scanner);

		return this.game;
	}

	private void parseBoardDimension(String input) throws ParserException {
		Matcher matcher = BOARD_DIMENSION_PATTERN.matcher(input);
		if (matcher.matches()) {
			int width = Integer.parseInt(matcher.group(1));
			int height = Integer.parseInt(matcher.group(2));

			this.game.setDimension(width, height);
		} else {
			throw new ParserException(String.format("Invalid board dimensions: %s", input));
		}
	}

	private void parsePlayers(Scanner scanner) throws ParserException {
		while (scanner.hasNextLine()) {
			this.game.addPlayer(parsePlayer(scanner.nextLine()));
		}
		if (game.players().size() < 2) {
			throw new ParserException("Not enough players defined.");
		}
	}

	private Player parsePlayer(String input) throws ParserException {
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
					this.game.getTile(
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
