package turtle;

import java.util.List;

public class Program
{
	private String program;
	private List<ICommand> commandList;

	public Program(String program) throws ParserException {
		this.program = program;
		this.commandList = Parser.parse(this.program);
	}

	public void execute(BoardMaker board) {
		for (ICommand c : this.commandList) {
			c.execute(board);
		}
	}
}
