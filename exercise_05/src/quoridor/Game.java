package quoridor;

import java.awt.Point;
import java.util.Arrays;


/**
 * Represents a Quoridor game.
 */
public class Game {
	private int width;
	private int height;

	private Player[] players;

	private Tile[][] tiles;

	public Game(int width, int height, Player players[]) {
		this.width = width;
		this.height = height;
		this.players = players;

		this.tiles = new Tile[width][height];
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

	public Player[] players() {
		/* It's a bit silly to wrap it in accessor, as it's a mutable
		 * object. */
		return this.players;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Game)) {
			return false;
		}

		Game game = (Game)other;

		return (
				game.width() == this.width &&
				game.height() == this.height &&
				game.players().equals(this.players) &&
				/* Array#equals() only works for shallow
				 * arrays, so can't use it here. */
				Arrays.deepEquals(game.tiles(), this.tiles)
		);
	}

	public String toString() {
		String players = "";
		for (Player p : this.players) {
			players += p.toString();
			players += "\n";
		}

		return String.format("Players:\n%s\n", players);
	}
}
