package turtle;

import java.util.List;

public class Program
{
	private String program;
	private List<Command> commandList;

	public Program(String program) throws ParserException {
		this.program = program;
		this.commandList = Parser.parse(this.program);
	}

	public void execute(BoardMaker board) {
	}
}
