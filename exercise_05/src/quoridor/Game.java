package quoridor;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Represents a Quoridor game.
 */
public class Game {
	private int width;
	private int height;

	private List<Player> players;

	private List<List<Tile>> tiles;

	public Game(int width, int height, List<Player> players) {
		this.width = width;
		this.height = height;
		this.players = players;

		this.tiles = new ArrayList<List<Tile>>();
	}

	public void setDimension(int width, int height) {
		this.width = width;
		this.height = height;

		this.tiles = new ArrayList<List<Tile>>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				this.tiles.get(i).add(new Tile(new Point(i, j)));
			}
		}
	}

	public int width() {
		return this.width;
	}

	public int height() {
		return this.height;
	}

	public List<List<Tile>> tiles() {
		/* It's a bit silly to wrap it in accessor, as it's a mutable
		 * object. */
		return this.tiles;
	}

	public List<Player> players() {
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
				game.tiles().equals(this.tiles)
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
	
	/**
	 * Checks wheather a tile is occupied or not 
	 */
	public boolean checkOccupation()
	{
		//TODO
		return false;
	}
}
