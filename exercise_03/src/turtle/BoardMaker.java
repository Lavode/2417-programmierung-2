package turtle;

import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

public class BoardMaker {
	private boolean[][] board;
	private final static int SIZE = 100;
	private Point position;
	private boolean autoTouch = true;

	private List<Command> cmdList;

	public Point getPosition() {
		return this.position;
	}

	public boolean currentField() {
		return getFieldAt(this.position);
	}

	public boolean getFieldAt(Point position) {
		return this.board[position.x][position.y];
	}

	/**
	 * Parse the given turtle program and evaluate it. Render the trail as
	 * described in the problem description and return a SIZExSIZE board
	 * corresponding to the evaluated path.
	 *
	 * @param turtleProgram input program according to specification. may also contain invalid text!
	 * @return SIZExSIZE boolean board, where true values denote "red trail".
	 */
	public boolean[][] makeBoardFrom(String turtleProgram) throws ParserException {
		// Reset board
		this.initialBoard();

		Program program = new Program(turtleProgram);
		program.execute(this);

		return this.board;
	}

	public boolean[][] initialBoard(int size) {
		this.board = new boolean[size][size];
		this.position = new Point(size / 2, size / 2);

		return this.board;
	}

	/**
	 * Create a new board and return it.
	 * @return board, must be of size SIZExSIZE.
	 */
	public boolean[][] initialBoard() {
		return this.initialBoard(SIZE);
	}

	public void moveUp(int count) {
		for (int i = 1; i <= count; i++) {
			this.moveUp();
		}
	}

	public void moveUp() {
		if (this.position.y == 0) {
			this.position.y = this.board.length - 1;
		} else {
			this.position.y -= 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
	}


	public void moveDown(int count) {
		for (int i = 1; i <= count; i++) {
			this.moveDown();
		}
	}

	public void moveDown() {
		if (this.position.y == this.board.length - 1) {
			this.position.y = 0;
		} else {
			this.position.y += 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
	}


	public void moveLeft(int count) {
		for (int i = 1; i <= count; i++) {
			this.moveLeft();
		}
	}

	public void moveLeft() {
		if (this.position.x == 0) {
			this.position.x = this.board.length -1;
		} else {
			this.position.x -= 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
	}


	public void moveRight(int count) {
		for (int i = 1; i <= count; i++) {
			this.moveRight();
		}
	}

	public void moveRight() {
		if (this.position.x == this.board.length - 1) {
			this.position.x = 0;
		} else {
			this.position.x += 1;
		}
		if (this.autoTouch) {
			this.touchField();
		}
	}


	// @TODO: Lots of repetitive code here.
	public void moveUpRight(int count) {
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveRight();
			this.moveUp();
			this.touchField();
		}
		this.autoTouch = true;
	}

	public void moveUpLeft(int count) {
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveLeft();
			this.moveUp();
			this.touchField();
		}
		this.autoTouch = true;
	}

	public void moveDownRight(int count) {
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveRight();
			this.moveDown();
			this.touchField();
		}
		this.autoTouch = true;
	}

	public void moveDownLeft(int count) {
		// As we want to draw a diagonal line, rather than a
		// zig-zag-line, we'll disable automatic touching of fields.
		this.autoTouch = false;
		for (int i = 1; i <= count; i++) {
			this.moveLeft();
			this.moveDown();
			this.touchField();
		}
		this.autoTouch = true;
	}


	private void touchField() {
		this.board[this.position.x][this.position.y] = true;
	}
}
