/* TODO: available walls
 * TODO: target tiles
 */

package quoridor;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.awt.Point;

/**
 * Parses a Quoridor file specification v2 and creates a {@link Game} instance.
 *
 * Internally, the parser will construct a game object piece-by-piece, but its
 * public API will take care of either returning a full game object, or
 * throwing an exception.
 */
public class ParserV2 extends Parser
{
	private static Pattern BOARD_DIMENSION_AND_WALL_COUNT_PATTERN = Pattern.compile("^(\\d+) (\\d+) (\\d+)( \\d+)+$");
	private static Pattern BOARD_ROW_PATTERN = Pattern.compile("^[#a-zA-Z ]+$");
	private static Pattern PLAYER_ENTRY_PATTERN = Pattern.compile("^(.) (.+)$");

	private Game game;
	private List<Integer> playerWallAllowances;
	private HashMap<Character, Point> playerStartingPositions = new HashMap<Character, Point>();
	private HashMap<Character, List<Point>> playerTargetPositions = new HashMap<Character, List<Point>>();
	private int width;
	private int height;

	public ParserV2() {
		this.game = new Game();
	}

	public Game parse(String input) throws ParserException {
		Scanner scanner = new Scanner(input);

		if (!scanner.hasNextLine()) {
			throw new ParserException(String.format("Input empty", input));
		}

		this.parseBoardDimensionAndWallCount(scanner);
		this.parseBoard(scanner);


		// for (Map.Entry<Character, Point> entry : this.playerStartingPositions.entrySet()) {
		// 	System.out.println(String.format("%s starts at %s / %s", entry.getKey(), entry.getValue().x, entry.getValue().y));
		// }

		// for (Map.Entry<Character, List<Point>> entry : this.playerTargetPositions.entrySet()) {
		// 	System.out.println(String.format("%s target positions:", entry.getKey()));
		// 	for (Point p : entry.getValue()) {
		// 		System.out.println(String.format("- %s / %s", p.x, p.y));
		// 	}

		// }

		this.parsePlayers(scanner);
		scanner.close();

		return this.game;
	}

	private void parseBoardDimensionAndWallCount(Scanner scanner) throws ParserException {
		String input = scanner.nextLine();
		Matcher matcher = BOARD_DIMENSION_AND_WALL_COUNT_PATTERN.matcher(input);
		if (matcher.matches()) {
			this.width = Integer.parseInt(matcher.group(1));
			this.height = Integer.parseInt(matcher.group(2));
			this.game.setDimension(this.width, this.height);

			/* First two entries are width and height, which we'll discard. */
			String[] wallAllowances = input.split(" ");
			this.playerWallAllowances = new ArrayList<Integer>(wallAllowances.length - 2);
			for (int i = 2; i < wallAllowances.length; i++) {
				this.playerWallAllowances.add(Integer.parseInt(wallAllowances[i]));
			}

		} else {
			throw new ParserException(String.format("Invalid board dimensions or player wall counts: %s", input));
		}
	}

	private void parseBoard(Scanner scanner) throws ParserException {
		for (int i = 0; i < this.height; i++) {
			if (scanner.hasNextLine()) {
				String input = scanner.nextLine();
				Matcher matcher = BOARD_ROW_PATTERN.matcher(input);
				if (matcher.matches()) {
					int j = 1;
					for (char c : input.toCharArray()) {
						if (c == '#') {
						} else if (c == ' ') {
						} else if (Character.isLowerCase(c)) {
							/* Lower case alphabetic char denotes a destination tile, store for later usage. */
							if (this.playerTargetPositions.get(c) == null) {
								this.playerTargetPositions.put(c, new ArrayList<Point>());
							}
							this.playerTargetPositions.get(c).add(new Point(j, i + 1));
						} else if (Character.isUpperCase(c)) {
							/* Upper case alphabetic denotes a starting position, store for later usage. */
							this.playerStartingPositions.put(c, new Point(j, i + 1));
						}
						j++;
					}
				} else {
					throw new ParserException(String.format("Invalid board row: %s", input));
				}
			} else {
				throw new ParserException(String.format("Not enough rows in board defintion. Expected: %i, Got: %i", this.height, i));
			}
		}
	}

	private void parsePlayers(Scanner scanner) throws ParserException {
		while (scanner.hasNextLine()) {
			String input = scanner.nextLine();
			this.game.addPlayer(parsePlayer(input));
		}
	}

	private Player parsePlayer(String input) throws ParserException {
		Matcher matcher = PLAYER_ENTRY_PATTERN.matcher(input);
		if (matcher.matches()) {
			char playerSign = matcher.group(1).charAt(0);
			String playerName = matcher.group(2);
			Point startingPosition = this.playerStartingPositions.get(playerSign);
			Tile startingTile = this.game.getTile(startingPosition.x, startingPosition.y);
			return new Player(
					playerName,
					playerSign,
					startingTile,
					null
			);
		} else {
			throw new ParserException(String.format("Invalid player entry: %s", input));
		}
	}
}
