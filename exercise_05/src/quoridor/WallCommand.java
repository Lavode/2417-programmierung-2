package quoridor;

import java.awt.Point;

public class WallCommand implements ICommand
{
	private Point from;
	private Point to;

	public WallCommand(Point from, Point to) {
		this.from = from;
		this.to = to;
	}

	public Point from() {
		return this.from;
	}

	public Point to() {
		return this.to;
	}

	public void execute(Player player) {
		player.placeWall(this.from, this.to);
	}

	public boolean equals(Object other) {
		if (!(other instanceof WallCommand)) {
			return false;
		}


		WallCommand cmd = (WallCommand)other;

		return (
				this.from.x == cmd.from().x &&
				this.from.y == cmd.from().y &&
				this.to.x   == cmd.to().x &&
				this.to.y   == cmd.to().y
		);
	}

	public String toString() {
		return String.format(
				"Wall: (%s, %s) -> (%s, %s)",
				from.x, from.y, to.x, to.y
		);
	}
}
