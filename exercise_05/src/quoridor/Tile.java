package quoridor;

public class Tile {
	private int x;
	private int y;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Tile)) {
			return false;
		}

		Tile tile = (Tile)other;

		return (
				tile.x() == this.x &&
				tile.y() == this.y
		);
	}
}
