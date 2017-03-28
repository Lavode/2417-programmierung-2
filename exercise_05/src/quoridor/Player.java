package quoridor;

import java.awt.Point;

public class Player {
	private Game game;
	private String name;
	private char sign;

	private Tile tile;
	private Target target;

	public enum Target {
		LEFT, RIGHT, UP, DOWN
	}

	public Player(String name, char sign, Tile tile, Target target) {
		this.name = name;
		this.sign = sign;
		this.tile = tile;
		this.target = target;
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

	public void moveUp()
	{
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x, current.position().y - 1);
		
		if(!(next.isOccupied()))
		{
			current.leave();
			next.enter(this);
		}
	}
	
	public void moveDown()
	{
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x, current.position().y + 1);
		
		if(!(next.isOccupied()))
		{
			current.leave();
			next.enter(this);
		}
	}
	
	public void moveRight()
	{
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x + 1, current.position().y);
		
		if(!(next.isOccupied()))
		{
			current.leave();
			next.enter(this);
		}
	}
	
	public void moveLeft()
	{
		Tile current = this.currentPosition();
		Tile next = game.getTile(current.position().x - 1, current.position().y);
		
		if(!(next.isOccupied()))
		{
			current.leave();
			next.enter(this);
		}
	}
}
