package quoridor;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;


/**
 * Represents a Quoridor game.
 */
public class Game {
	private int width;
	private int height;
	
	private int playerAmountOfWalls;
	
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
		this.playerAmountOfWalls = 0;
		this.winner = null;
		this.currentPlayer = players.get(0);
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
	 * Tell game how many Walls each player can use! (During step by step init)
	 * Variable equals 0 if this method is never called.
	 * @param i amount of Walls
	 */
	public void setWallAmount(int i)
	{
		this.playerAmountOfWalls = i;
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

		return String.format("Size: %s\nPlayers:\n%s\n", size, players);
	}
	
	public boolean isNotOver()
	{
		return this.winner == null;
	}

	/**
	 * Askes the player what he wants to do next.
	 */
	public String askOrder()
	{
		//TODO ask player for order
		return "";
	}
	
	public void play(Parser parser, Renderer renderer)
	{
		//TODO implement main gameloop
		boolean validInput = false;
		String order;
		while(this.isNotOver())
		{
			while(!validInput)
			{
				order = this.askOrder();
				try
				{
					if(parser.orderType(order) == Player.CommandType.MOVEMENT)
					{
						try
						{
							if(order.toLowerCase() == "u")
								currentPlayer.moveUp();
							else if(order.toLowerCase() == "d")
								currentPlayer.moveDown();
							else if(order.toLowerCase() == "l")
								currentPlayer.moveLeft();
							else if(order.toLowerCase() == "r")
								currentPlayer.moveRight();
							
							if(currentPlayer.hasFinished())
								this.winner = currentPlayer;
							validInput = true;
						}
						catch(TileOccupiedException e)
						{
							System.out.println("You try to move onto a occupied Tile, please make another move!");
						}
					}
					else
					{
						int i[] = parser.parseWallplacement();
						currentPlayer.placeWall(i[0], i[1], i[2], i[3]);
						validInput = true;
					}
				}
				catch (ParserException e)
				{
					System.out.println("Your order could not be read, please repeat!");
				}
				System.out.println(renderer.render());
			}
			
			this.switchCurrentPlayer();
		}
	}

	public static void main(String[] args) throws ParserException, IOException {
		Parser parser = new Parser();
		Game game = parser.parseFromFile("games/game1.txt");
		System.out.println(game);
		Renderer renderer = new Renderer(game);
		System.out.println(renderer.render());
		
		game.play(parser, renderer);
	}
}
