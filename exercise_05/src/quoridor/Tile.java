package quoridor;

import java.awt.Point;

public class Tile {
	private Point position;

	public Tile(Point position) {
		this.position = position;
	}

	public Point position() {
		return this.position;
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
}
