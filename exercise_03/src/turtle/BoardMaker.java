package turtle;

public class BoardMaker {
	private boolean[][] board;
	private final static int SIZE = 100;

	/**
	 * Parse the given turtle program and evaluate it. Render the trail as
	 * described in the problem description and return a SIZExSIZE board
	 * corresponding to the evaluated path.
	 *
	 * @param turtleProgram input program according to specification. may also contain invalid text!
	 * @return SIZExSIZE boolean board, where true values denote "red trail".
	 */
	public boolean[][] makeBoardFrom(String turtleProgram) throws ParserException {

		// TODO: Read text below!
		// You should handle parsing of the program in a different class.
		// That class should create and store individual programs, which
		// can then be executed.
		//
		// An example of how this method could be implemented follows.
		// Please note that this is not real Java code; you will have
		// to come up with your own classes and structures!
		/*
		// You can create a new board each time makeBoardFrom is called.
		boolean[][] board = new boolean[SIZE][SIZE];

		// Create a parser that accepts a program and creates individual
		// programs.
		CommandParser parser = new CommandParser();
		parser.parse(turtleProgram);

		// Iterate over the parsed commands and keep track of the turtle.
		for each parsed command {
			execute the command on the board and update the state of the turtle
		}
		*/

		// TODO: remove this and return a board instead
		throw new UnsupportedOperationException();
	}

	/**
	 * Create a new board and return it.
	 * @return board, must be of size SIZExSIZE.
	 */
	public boolean[][] initialBoard() {
		// TODO: remove this and return a board instead
		throw new UnsupportedOperationException();
	}
}