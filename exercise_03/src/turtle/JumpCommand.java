package turtle;

import java.awt.Point;

public class JumpCommand implements ICommand
{
	private int x;
	private int y;

	public JumpCommand(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void execute(BoardMaker board) {
		board.jump(this.x, this.y);
	}

	public boolean equals(Object other) {
		JumpCommand cmd = (JumpCommand)other;
		return (this.x == cmd.x && this.y == cmd.y);
	}
}
