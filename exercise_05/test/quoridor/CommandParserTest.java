package quoridor;

import java.awt.Point;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CommandParserTest
{
	private CommandParser parser;

	@Before
	public void init() {
		Game game = new Game();
		game.setDimension(3, 3);

		this.parser = new CommandParser(game);
	}

	@Test
	public void parseHandlesMoveUpCommand() throws ParserException {
		ICommand cmd = this.parser.parse("u");
		assertEquals(new MoveCommand(MoveCommand.Direction.UP), cmd);
	}

	@Test
	public void parseHandlesMoveDownCommand() throws ParserException {
		ICommand cmd = this.parser.parse("d");
		assertEquals(new MoveCommand(MoveCommand.Direction.DOWN), cmd);
	}

	@Test
	public void parseHandlesMoveLeftCommand() throws ParserException {
		ICommand cmd = this.parser.parse("l");
		assertEquals(new MoveCommand(MoveCommand.Direction.LEFT), cmd);
	}

	@Test
	public void parseHandlesMoveRightCommand() throws ParserException {
		ICommand cmd = this.parser.parse("r");
		assertEquals(new MoveCommand(MoveCommand.Direction.RIGHT), cmd);
	}

	@Test
	public void parseIsCaseInsensitive() throws ParserException {
		ICommand cmd = this.parser.parse("R");
		assertEquals(new MoveCommand(MoveCommand.Direction.RIGHT), cmd);
	}

	@Test
	public void parseHandlesWallCommand() throws ParserException {
		ICommand cmd = this.parser.parse("wall 1 1 1 2");
		assertEquals(new WallCommand(new Point(1, 1), new Point(1, 2)), cmd);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnWallCommandReferencingTilesOutsideOfGame() throws ParserException {
		this.parser.parse("wall 3 3 4 3");
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnWallCommandWithIdenticalCoordinates() throws ParserException {
		this.parser.parse("wall 2 2 2 2");
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnWallCommandWithTooFarApartCoordinates() throws ParserException {
		this.parser.parse("wall 1 1 1 3");
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnWallCommandWithDiagonalCoordinates() throws ParserException {
		this.parser.parse("wall 1 1 2 2");
	}


	@Test(expected = ParserException.class)
	public void parseThrowsParserExceptionOnInvalidCommand() throws ParserException {
		this.parser.parse("udlr");
	}
}
