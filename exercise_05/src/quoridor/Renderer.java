package quoridor;

import java.util.*;

/**
 * Renders a {@link Game} object.
 */
public class Renderer {
	private static final char WALL_SIGN = '#';
	private Game game;
	private char[][] output;

	public Renderer(Game game) {
		this.game = game;
	}

	public String render() {
		resetOutput();
		renderWalls();
		renderPlayers();

		return this.buildOutput();
	}

	private String buildOutput() {
		StringBuilder out = new StringBuilder();
		for (char[] col : this.output) {
			for (char c : col) {
				out.append(c);
			}
			out.append("\n");
		}

		return out.toString();
	}

	private void resetOutput() {
		/* Add one additional row/column on each side which will
		 * contain the wall. */
		this.output = new char[this.game.width() + 2][this.game.height() + 2];

		for (int x = 0; x < this.output.length; x++) {
			for (int y = 0; y < this.output[0].length; y++) {
				this.output[x][y] = ' ';
			}
		}
	}

	private void renderPlayers() {
		for (Player player : this.game.players()) {
			/* So, logically convluted crap ahead.
			 * Player position is tracked via a 1-based coordinate system.
			 * Our output array is 0-based.
			 * Our output array contains an additional row/column on each side for the wall.
			 *
			 * Hence, when mapping the player's position to an
			 * index in the output array, we have to subtract one
			 * to switch from 1-basedness to 0-basedness, then add
			 * 1 to accomodate for the wall column/row. Which
			 * leaves us with a difference of 0. */
			this.output[player.position().x][player.position().y] = player.sign();
		}
	}

	private void renderWalls() {
		renderHorizontalLine(0, WALL_SIGN);
		renderHorizontalLine(this.output.length - 1, WALL_SIGN);
		renderVerticalLine(0, WALL_SIGN);
		renderVerticalLine(this.output[0].length - 1, WALL_SIGN);
	}

	private void renderVerticalLine(int height, char symbol) {
		for (int x = 0; x < this.output.length; x++) {
			this.output[x][height] = symbol;
		}
	}

	private void renderHorizontalLine(int width, char symbol) {
		for (int y = 0; y < this.output[width].length; y++) {
			this.output[width][y] = symbol;
		}
	}
}
