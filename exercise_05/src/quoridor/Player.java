package quoridor;

import java.awt.Point;

/**
 * 
 * @author pasca
 *
 */
public class Player {
	private Game game;
	private final String name;
	private final char sign;

	private Tile tile;
	private  final Target target;

	public enum Target {
		LEFT, RIGHT, UP, DOWN
	}

	public Player(String name, char sign, Tile tile, Target target) {
		assert !tile.isOccupied();
		this.name = name;
		this.sign = sign;
		this.tile = tile;
		this.tile.enter(this);
		this.target = target;
		this.game = null;
	}
	
	/**
	 * @return true if player is in a game, false otherwise
	 */
	public boolean hasGame()
	{
		return this.game != null;
	}
	
	/**
	 * lets a player enter a game.
	 * 
	 * @param game which player should enter
	 */
	public void enterGame(Game game)
	{
		this.game = game;
	}

	/**
	 * @return Players name
	 */
	public String name() {
		return this.name;
	}

	/**
	 * @return Players sign
	 */
	public char sign() {
		return this.sign;
	}

	/**
	 * @return Tile on which Player is currently standing
	 */
	public Tile currentPosition() {
		return this.tile;
	}

	/**
	 * @return Players target
	 */
	public Target target() {
		return this.target;
	}

	/**
	 * @return Point object of Players current Position
	 */
	public Point position() {
		return this.tile.position();
	}

	/**
	 * Checks two objects for equality
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Player)) {
			return false;
		}

		Player player = (Player)other;

		return (
				player.name().equals(this.name) &&
				player.sign() == this.sign &&
				player.currentPosition().equals(this.tile) &&
				player.target() == this.target
		);
	}

	/**
	 * @return String representation of player object, suitable for
	 * consumption by humans.
	 */
	public String toString() {
		return String.format("<%s> [%s] @ (%s, %s) -> %s", this.name, this.sign, this.tile.position().x, this.tile.position().y, this.target);
	}
	
	private void moveFromTo(Tile current, Tile next) throws TileOccupiedException
	{
		if(!(next.isOccupied()))
		{
			current.leave();
			next.enter(this);
			this.tile = next;
			assert this.invariant();
		}	
		else
		{
			assert this.invariant();
			throw new TileOccupiedException("Field you want to move to is occupied!");
		}
	}

	/**
	 * Tries to move the player up by one field
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveUp() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		assert game.isValidPosition(current.position().x, current.position().y - 1);
		Tile next = game.getTile(current.position().x, current.position().y - 1);
		
		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x, current.position().y - 1);
		this.moveFromTo(current, next);
	}
	
	/**
	 * Tries to move the player down by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveDown() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x, current.position().y + 1);

		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x, current.position().y + 1);
		
		this.moveFromTo(current, next);
	}
	
	/**
	 * tries to move the player to the right by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveRight() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x + 1, current.position().y);

		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x + 1, current.position().y);
		
		this.moveFromTo(current, next);
	}
	
	/**
	 * tries to move the player to the left by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveLeft() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x - 1, current.position().y);

		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x - 1, current.position().y);
		
		this.moveFromTo(current, next);
	}
	
	private boolean invariant()
	{
		return this.tile != null;
	}
}
