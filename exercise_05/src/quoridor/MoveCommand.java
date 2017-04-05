package quoridor;

public class MoveCommand implements ICommand
{
	private Direction direction;

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public MoveCommand(Direction direction) {
		this.direction = direction;
	}

	public Direction direction() {
		return this.direction;
	}

	public void execute(Player player) throws TileOccupiedException {
		switch (this.direction) {
			case UP:
				player.moveUp();
				break;
			case DOWN:
				player.moveDown();
				break;
			case LEFT:
				player.moveLeft();
				break;
			case RIGHT:
				player.moveRight();
				break;
		}
	}

	public boolean equals(Object other) {
		if (!(other instanceof MoveCommand)) {
			return false;
		}


		MoveCommand cmd = (MoveCommand)other;

		return this.direction == cmd.direction();
	}
}
