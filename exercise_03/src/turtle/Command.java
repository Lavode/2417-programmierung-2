package turtle;

public class Command
{
	public enum Direction {
		NORTH, EAST, SOUTH, WEST
	}

	Direction direction;

	public Command(Direction direction) {
		this.direction = direction;
	}

	public boolean equals(Object other) {
		Command cmd = (Command)other;
		return cmd.direction == this.direction;
	}
}
