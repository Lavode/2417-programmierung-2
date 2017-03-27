package quoridor;

import java.awt.Point;

public class Tile {
	private Point position;
	private Player player;

	public Tile(Point position) {
		this.position = position;
		this.player = null;
	}

	public Point position() {
		return this.position;
	}

	public Player player()
	{
		return this.player;
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
		return !(this.player == null);
	}
}
