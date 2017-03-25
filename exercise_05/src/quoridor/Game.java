package quoridor;

import java.awt.Point;
import java.util.Arrays;


/**
 * Represents a Quoridor game.
 */
public class Game {
	private int width;
	private int height;

	private Player player1;
	private Player player2;

	private Tile[][] tiles;

	public Game(int width, int height, Player player1, Player player2) {
		this.width = width;
		this.height = height;
		this.player1 = player1;
		this.player2 = player2;

		this.tiles = new Tile[width][height];
	}

	public Player player1() {
		return this.player1;
	}

	public Player player2() {
		return this.player2;
	}

	public int width() {
		return this.width;
	}

	public int height() {
		return this.height;
	}

	public Tile[][] tiles() {
		/* It's a bit silly to wrap it in accessor, as it's a mutable
		 * object. */
		return this.tiles;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Game)) {
			return false;
		}

		Game game = (Game)other;

		return (
				game.width() == this.width &&
				game.height() == this.height &&
				game.player1().equals(this.player1) &&
				game.player2().equals(this.player2) &&
				/* Array#equals() only works for shallow
				 * arrays, so can't use it here. */
				Arrays.deepEquals(game.tiles(), this.tiles)
		);
	}
}
