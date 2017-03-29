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
	
	public boolean hasGame()
	{
		return this.game != null;
	}
	
	public void enterGame(Game game)
	{
		this.game = game;
	}

	public String name() {
		return this.name;
	}

	public char sign() {
		return this.sign;
	}

	public Tile currentPosition() {
		return this.tile;
	}

	public Target target() {
		return this.target;
	}

	public Point position() {
		return this.tile.position();
	}

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

	public String toString() {
		return String.format("<%s> [%s] @ (%s, %s) -> %s", this.name, this.sign, this.tile.position().x, this.tile.position().y, this.target);
	}

	public void moveUp() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		assert game.isValidPosition(current.position().x, current.position().y - 1);
		Tile next = game.getTile(current.position().x, current.position().y - 1);
		
		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x, current.position().y - 1);
		
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
	
	public void moveDown() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x, current.position().y + 1);

		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x, current.position().y + 1);
		
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
	
	public void moveRight() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x + 1, current.position().y);

		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x + 1, current.position().y);
				
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
	
	public void moveLeft() throws TileOccupiedException
	{
		assert this.invariant();
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x - 1, current.position().y);

		assert this.hasGame() && this.currentPosition() != null;
		assert game.isValidPosition(current.position().x - 1, current.position().y);
		
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
	
	private boolean invariant()
	{
		return this.tile != null;
	}
}
