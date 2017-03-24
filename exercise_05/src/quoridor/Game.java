package quoridor;

import java.awt.Point;


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
}
