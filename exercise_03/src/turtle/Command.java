package turtle;

public class Command
{
	public enum Direction {
		NORTH, EAST, SOUTH, WEST
	}

	Direction direction;
	int count;

	public Command(Direction direction) {
		this.direction = direction;
		this.count = 1;
	}

	public Command(Direction direction, int count) {
		this.direction = direction;
		this.count = count;
	}

	public void execute(BoardMaker board) {
		switch(this.direction) {
			case NORTH:
				board.moveUp(this.count);
				break;
			case EAST:
				board.moveRight(this.count);
				break;
			case SOUTH:
				board.moveDown(this.count);
				break;
			case WEST:
				board.moveLeft(this.count);
				break;
		}
	}

	public boolean equals(Object other) {
		Command cmd = (Command)other;
		return (cmd.direction == this.direction && cmd.count == this.count);
	}
}
