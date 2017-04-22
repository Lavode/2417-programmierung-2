package quoridor;

import java.awt.Point;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


/**
 * Represents a Quoridor game.
 */
public class Game {
	private int width;
	private int height;

	private Player winner;
	private Player currentPlayer;

	private List<Player> players;

	private List<List<Tile>> tiles;

	/**
	 * Create a new instance of this class.
	 *
	 * This will yield you an incomplete game object, which you will have
	 * to complete by calling further methods on.
	 */
	public Game() {
		this.tiles = new ArrayList<List<Tile>>();
		this.players = new ArrayList<Player>();
		this.winner = null;
	}

	/**
	 * lets next player be new currentPlayer
	 */
	public void switchCurrentPlayer()
	{
		int i = players.indexOf(currentPlayer);
		currentPlayer = players.get((i+1) % players.size());
	}

	/**
	 * Create a new instance of this class.
	 *
	 * This will yield you a fully-complete game object, which can be used
	 * straight away. Nevertheless, you can call additional methods - to
	 * e.g. add players - if you so desire.
	 *
	 * @param width Width of game board. Must be > 0;
	 * @param height Height of game board. Must be > 0;
	 * @param players List of players participating in game.
	 */
	public Game(int width, int height, List<Player> players) {
		setDimension(width, height);
		this.players = new ArrayList<Player>();
		for (Player p : players) {
			this.players.add(p);
		}
	}

	/**
	 * Add additional player to the game.
	 *
	 * @param player Player which to add.
	 */
	public void addPlayer(Player player) {
		this.players.add(player);
		if (this.currentPlayer == null) {
			this.currentPlayer = player;
		}
	}

	/**
	 * Checks Position (@param x, @param y) for beeing a valid Positon
	 * @return true if valid Position, false otherwise
	 */
	public boolean isValidPosition(int x, int y)
	{
		return 0 < x && x <= width && 0 < y && y <= height;
	}

	/**
	 * Set dimensions of game board.
	 *
	 * This will create new regular tiles to fill the board - overwriting
	 * whatever was there before.
	 *
	 * @param width Width of game board. Must be > 0;
	 * @param height Height of game board. Must be > 0;
	 */
	public void setDimension(int width, int height) {
		assert(width > 0);
		assert(height > 0);

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

	/**
	 * Return tile at given position.
	 *
	 * Tile coordinates are 1-indexed, i.e. they go from 1 up to the
	 * board's width/height.
	 *
	 * @param x X / horizontal coordinate of tile. Must be >= 1 and <= board height.
	 * @param y Y / vertical coordinate of tile. Must be >= 1 and <= board width.
	 *
	 * @return Tile at given coordinates.
	 */
	public Tile getTile(int x, int y) {
		assert(x >= 1);
		assert(x <= this.width);
		assert(y >= 1);
		assert(y <= this.height);

		return this.tiles.get(x - 1).get(y - 1);
	}

	/**
	 * Get tile at given position.
	 *
	 * See {@link Game#getTile} for further details.
	 */
	public Tile getTile(Point position) {
		return this.getTile(position.x, position.y);
	}

	/**
	 * @return Game board's width.
	 */
	public int width() {
		return this.width;
	}

	/**
	 * @return Game board's height.
	 */
	public int height() {
		return this.height;
	}

	/**
	 * @return Game board's tiles.
	 */
	public List<List<Tile>> tiles() {
		/* It's a bit silly to wrap it in accessor, as it's a mutable
		 * object. */
		return this.tiles;
	}

	/**
	 * @return Game's players.
	 */
	public List<Player> players() {
		/* It's a bit silly to wrap it in accessor, as it's a mutable
		 * object. */
		return this.players;
	}

	/**
	 * @return Player whose turn it is.
	 */
	public Player currentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Build wall between two points.
	 *
	 * @param from First endpoint of wall.
	 * @param to Second endpoint of wall.
	 *
	 * @throws TileOccupiedException if building the wall fails e.g. due to
	 * a tile being occupied by a player or wall, or if it would cut off
	 * one of the players.
	 */
	public void buildWall(Point from, Point to) throws CommandRollbackException {
		getTile(from).setWall();
		getTile(to).setWall();

		if (!allPlayersAbleToReachTarget()) {
			getTile(from).unsetWall();
			getTile(to).unsetWall();
			/* Note that we don't have to refund the player, as we
			 * throw an exception before he substracts.
			 * However, relying on an implementation detail like
			 * this is bound to bite us in the ass sooner or later. */

			throw new CommandRollbackException("You may not box in players.");
		}
	}

	/**
	 * Check whether all players are able to reach their targets.
	 */
	private boolean allPlayersAbleToReachTarget() {
		for (Player player : this.players) {
			if (!player.canReachTarget()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Compare two game objects for equality.
	 *
	 * They are considered equal if:
	 * - Their width and height is equal
	 * - All their players are equal
	 * - All their tiles are equal
	 *
	 * @return Whether the two objects are equal.
	 */
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

	/**
	 * @return String representation of game object, suitable for
	 * consumption by humans.
	 */
	public String toString() {
		String size = String.format("%sx%s", this.width, this.height);
		String players = "";
		for (Player p : this.players) {
			players += p.toString();
			players += "\n";
		}

		return String.format("Size: %s\nPlayers:\n%s", size, players);
	}

	/**
	 * @return True if one of the game's players has reached his target.
	 */
	public boolean isOver() {
		for (Player player : this.players) {
			if (player.hasFinished()) {
				this.winner = player;
				return true;
			}
		}

		return false;
	}

	/**
	 * Main game loop. Continuously ask players for commands and execute
	 * them, until one of the players has won the game.
	 *
	 * @param renderer Renderer which to use to render game state.
	 */
	public void play(Renderer renderer)
	{
		for (Player player : this.players) {
			player.enterGame(this);
		}

		UserInteraction ui = new UserInteraction(this);

		while(!this.isOver()) {
			ICommand command = ui.askNextCommand();
			try {
				command.execute(this.currentPlayer);
				this.switchCurrentPlayer();
			} catch (TileOccupiedException e) {
				System.out.println("You tried to move onto an occupied Tile, please make another move!");
			} catch (CommandRollbackException e) {
				System.out.println(String.format("\n>>>> Error <<<<<\n%s\nPlease enter another command.\n", e.getMessage()));
			}

			System.out.println(renderer.render());
		}

		System.out.println(String.format("Congratulations %s, you have won!", this.winner.name()));
	}

	/**
	 * Read specification from gamess/game1.txt, and start game.
	 */
	public static void main(String[] args) throws ParserException, IOException {
		Game game = Parser.parseFromFile("games/game3.txt");
		Renderer renderer = new Renderer(game);
		System.out.println(renderer.render());

		game.play(renderer);
	}

	/**
	 * Convert this game's board to a two-dimensional array of integers
	 * suitable for the pathfinding algorithm.
	 *
	 * Free (passable) tiles will be represented as a 0, whereas walls will
	 * be represented as a 1.
	 *
	 * @return Two-dimensonal integer array representing game board.
	 */
	public int[][] toPathFindingBoard() {
		int[][] board = new int[this.width][this.height];

		for (int i = 1; i <= this.width; i++) {
			for (int j = 1; j <= this.height; j++) {
				if (getTile(i, j).hasWall()) {
					board[i - 1][j - 1] = 1;
				} else {
					board[i - 1][j - 1] = 0;
				}
			}
		}
		return board;
	}
}
