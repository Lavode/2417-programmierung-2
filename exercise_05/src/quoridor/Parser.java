package quoridor;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Parses a Quoridor file specification and creates a {@link Game} instance.
 *
 * Internally, the parser will construct a game object piece-by-piece, but its
 * public API will take care of either returning a full game object, or
 * throwing an exception.
 */
public class Parser {
	private static Pattern BOARD_DIMENSION_PATTERN = Pattern.compile("^(\\d+) (\\d+)$");
	private static Pattern PLAYER_ENTRY_PATTERN = Pattern.compile("^(.+) (.) (\\d+) (\\d+) (\\w)$");

	private Game game;

	/** 
	 * Create new instance of Parser class.
	 */
	public Parser() {
		this.game = new Game();
	}

	/** 
	 * Read and parse a game specification from disk.
	 *
	 * @param path Path to file on filesystem wheree game specification resides.
	 *
	 * @throws ParserException if supplied game specification is invalid.
	 * @throws IOException if reading from given path fails for any reason.
	 *
	 * @return Game object corresponding to specification in file. 
	 */
	public Game parseFromFile(String path) throws ParserException, IOException {
		String input = new String(Files.readAllBytes(Paths.get(path)));
		return parse(input);
	}

	/**
	 * Read and parse a game specification from a string.
	 *
	 * @param input String containing game specification.
	 *
	 * @throws ParserException if supplied game specification is invalid.
	 *
	 * @return Game object corresponding to specification in given string.
	 */
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

	/** 
	 * Parse specification of board dimensions.
	 *
	 * This method will call the appropriate actions on the internal game
	 * object, and will not return anything.
	 *
	 * @param input String containing board dimension specification.
	 *
	 * @throws ParserException if supplied dimension specification is invalid.
	 */
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

	/** 
	 * Parse specification of players.
	 *
	 * This method will call the appropriate actions on the internal game
	 * object, and will not return anything.
	 *
	 * @param scanner Scanner object where player specifications - one per
	 * line - will be read from.
	 *
	 * @throws ParserException if supplied player specifications are invalid.
	 */
	private void parsePlayers(Scanner scanner) throws ParserException {
		while (scanner.hasNextLine()) {
			this.game.addPlayer(parsePlayer(scanner.nextLine()));
		}
		if (game.players().size() < 2) {
			throw new ParserException("Not enough players defined.");
		}
	}

	/** 
	 * Parse specification of player.
	 *
	 * This method will call the appropriate actions on the internal game
	 * object, and will not return anything.
	 *
	 * @param input String containing player specification.
	 *
	 * @throws ParserException if supplied player specification is invalid.
	 */
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
