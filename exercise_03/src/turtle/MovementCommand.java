package turtle;

public class MovementCommand implements ICommand
{
	public enum Direction {
		NORTH, EAST, SOUTH, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
	}

	Direction direction;
	int count;

	public MovementCommand(Direction direction) {
		this.direction = direction;
		this.count = 1;
	}

	public MovementCommand(Direction direction, int count) {
		this.direction = direction;
		this.count = count;
	}

	public void execute(BoardMaker board) {
		switch(this.direction) {
			case NORTH:
				board.moveUp(this.count);
				break;
			case NORTH_EAST:
				board.moveUpRight(this.count);
				break;
			case NORTH_WEST:
				board.moveUpLeft(this.count);
				break;
			case EAST:
				board.moveRight(this.count);
				break;
			case SOUTH:
				board.moveDown(this.count);
				break;
			case SOUTH_EAST:
				board.moveDownRight(this.count);
				break;
			case SOUTH_WEST:
				board.moveDownLeft(this.count);
				break;
			case WEST:
				board.moveLeft(this.count);
				break;
		}
	}

	public boolean equals(Object other) {
		MovementCommand cmd = (MovementCommand)other;
		return (cmd.direction == this.direction && cmd.count == this.count);
	}
}
