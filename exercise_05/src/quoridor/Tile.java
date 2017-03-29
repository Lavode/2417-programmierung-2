package quoridor;

import java.awt.Point;

/**
 * represents one Tile of a quoridor game
 * keeps track of its position in the game and of player beeing on this tile 
 * (resp. noone beeing on this tile)
 * 
 * @author Pascal Gerig
 * @author Michael Senn
 *
 */
public class Tile {
	private Point position;
	private Player player;

	public Tile(Point position) {
		this.position = position;
		this.player = null;
	}

	public String toString()
	{
		return "X: " + this.position.x + ", Y:" + this.position.y;
	}
	public Point position() {
		return this.position;
	}

	public Player player()
	{
		return this.player;
	}
	
	public void enter(Player player)
	{
		this.player = player;
	}
	
	public void leave()
	{
		this.player = null;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Tile)) {
			return false;
		}


		Tile tile = (Tile)other;

		return (
				tile.position().x == this.position.x &&
				tile.position().y == this.position.y
		);
	}
	
	public boolean isOccupied()
	{
		return !(this.player==null);
	}
}
