package turtle;

import java.util.List;
import java.util.ArrayList;
import java.awt.Point;

public class BoardMaker {
	private boolean[][] board;
	private final static int SIZE = 100;
	private Point position;
	private boolean autoTouch = true;

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

		assert(this.board.length == SIZE);
		return this.board;
	}

	/**
	 * (Re)initialize board with given size.
	 *
	 * This will purge the board, allowing (or requiring) a fresh start.
	 *
	 * @param size Length of board. Must be > 0.
	 */
	public boolean[][] initialBoard(int size) {
		assert(size > 0);

		this.board = new boolean[size][size];
		this.position = new Point(size / 2, size / 2);

		assert(this.board.length == size);
		return this.board;
	}

	/**
	 * Create a new board and return it.
	 * @return board, must be of size SIZExSIZE.
	 */
	public boolean[][] initialBoard() {
		return this.initialBoard(SIZE);
	}

	/** 
	 * Move upwards.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveUp(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveUp();
		}
	}

	/**
	 * Move one step upwards.
	 *
	 * Same wrap-around logic as with moveUpwards(int) applies.
	 */
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


	/** 
	 * Move downwards.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveDown(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveDown();
		}
	}

	/**
	 * Move one step downwards.
	 *
	 * Same wrap-around logic as with moveDown(int) applies.
	 */
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


	/** 
	 * Move towards the left.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveLeft(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveLeft();
		}
	}

	/**
	 * Move one step to the left.
	 *
	 * Same wrap-around logic as with moveLeft(int) applies.
	 */
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


	/** 
	 * Move towards the right.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveRight(int count) {
		assert(count > 0);
		for (int i = 1; i <= count; i++) {
			this.moveRight();
		}
	}

	/**
	 * Move one step to the right.
	 *
	 * Same wrap-around logic as with moveRight(int) applies.
	 */
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
	/** 
	 * Move towards the top right.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveUpRight(int count) {
		assert(count > 0);
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

	/** 
	 * Move towards the top left.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveUpLeft(int count) {
		assert(count > 0);
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

	/** 
	 * Move towards the bottom right.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveDownRight(int count) {
		assert(count > 0);
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

	/** 
	 * Move towards the bottom left.
	 *
	 * Hitting the border of the field will cause the character to wrap
	 * around, appearing on the other side.
	 *
	 * @param count Number of steps to take. Must be > 0.
	 */
	public void moveDownLeft(int count) {
		assert(count > 0);
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

	/**
	 * Jump to position on the field.
	 *
	 * @param x X coordinate. Must be >= 0.
	 * @param y Y coordinate. Must be >= 0.
	 */
	public void jump(int x, int y) {
		assert(x >= 0);
		assert(y >= 0);

		this.position.x = x;
		this.position.y = y;
	}


	private void touchField() {
		this.board[this.position.x][this.position.y] = true;
	}
}
