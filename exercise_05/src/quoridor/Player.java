package quoridor;

import java.awt.Point;

public class Player {
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

	public String name() {
		return this.name;
	}

	public char sign() {
		return this.sign;
	}

	public Tile tile() {
		return this.tile;
	}

	public Target target() {
		return this.target;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Player)) {
			return false;
		}

		Player player = (Player)other;

		return (
				player.name().equals(this.name) &&
				player.sign() == this.sign &&
				player.tile().equals(this.tile) &&
				player.target() == this.target
		);
	}

	public String toString() {
		return String.format("<%s> [%s] @ (%s, %s) -> %s", this.name, this.sign, this.tile.position().x, this.tile.position().y, this.target);
	}

	public void moveUp()
	{
		
	}
	
	public void moveDown()
	{
		
	}
	
	public void moveRight()
	{
		
	}
	
	public void moveLeft()
	{
		
	}
}
