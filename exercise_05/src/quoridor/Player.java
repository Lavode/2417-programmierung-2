package quoridor;

import java.awt.Point;

public class Player {
	private String name;
	private char sign;
	
	/*TODO shouldn't we use a Tile here since you can make a Tile keep 
	track of the player while the Point can't?*/ 
	private Point position;
	private Target target;

	public enum Target {
		LEFT, RIGHT, UP, DOWN
	}

	public Player(String name, char sign, Point position, Target target) {
		this.name = name;
		this.sign = sign;
		this.position = position;
		this.target = target;
	}

	public String name() {
		return this.name;
	}

	public char sign() {
		return this.sign;
	}

	public Point position() {
		return this.position;
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
				/* awt.Point implements no meaningful equals() */
				player.position().x == this.position.x &&
				player.position().y == this.position.y &&
				player.target() == this.target
		);
	}

	public String toString() {
		return String.format("<%s> [%s] @ (%s, %s) -> %s", this.name, this.sign, this.position.x, this.position.y, this.target);
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
