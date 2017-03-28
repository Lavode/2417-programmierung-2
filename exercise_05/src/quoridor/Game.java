package quoridor;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.IOException;


/**
 * Represents a Quoridor game.
 */
public class Game {
	private int width;
	private int height;

	private List<Player> players;

	private List<List<Tile>> tiles;

	public Game() {
		this.tiles = new ArrayList<List<Tile>>();
		this.players = new ArrayList<Player>();
	}

	public Game(int width, int height, List<Player> players) {
		setDimension(width, height);
		this.players = new ArrayList<Player>();
		for (Player p : players) {
			this.players.add(p);
		}
	}
	
	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public void setDimension(int width, int height) {
		this.width = width;
		this.height = height;

		this.tiles = new ArrayList<List<Tile>>();
		for (int i = 1; i <= width; i++) {
			this.tiles.add(new ArrayList<Tile>());
			for (int j = 1; j <= height; j++) {
				/* Bloody requirement to use 1-indexed
				 * coordinates */
				this.tiles.get(i - 1).add(new Tile(new Point(i, j)));
			}
		}
	}

	public Tile getTile(int x, int y) {
		return this.tiles.get(x - 1).get(y - 1);
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
			System.out.println("Instance");
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
		String size = String.format("%sx%s", this.width, this.height);
		String players = "";
		for (Player p : this.players) {
			players += p.toString();
			players += "\n";
		}

		return String.format("Size: %s\nPlayers:\n%s\n", size, players);
	}
	
	/**
	 * Checks wheather a tile is occupied or not 
	 */
	public boolean checkOccupation()
	{
		//TODO
		return false;
	}

	public static void main(String[] args) throws ParserException, IOException {
		Parser parser = new Parser();
		Game game = parser.parseFromFile("games/game1.txt");
		System.out.println(game);
		Renderer renderer = new Renderer(game);
		System.out.println(renderer.render());
	}
}
