package quoridor;

import java.awt.Point;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CommandParserTest
{
	@Test
	public void parseHandlesMoveUpCommand() throws ParserException {
		ICommand cmd = CommandParser.parse("u");
		assertEquals(new MoveCommand(MoveCommand.Direction.UP), cmd);
	}

	@Test
	public void parseHandlesMoveDownCommand() throws ParserException {
		ICommand cmd = CommandParser.parse("d");
		assertEquals(new MoveCommand(MoveCommand.Direction.DOWN), cmd);
	}

	@Test
	public void parseHandlesMoveLeftCommand() throws ParserException {
		ICommand cmd = CommandParser.parse("l");
		assertEquals(new MoveCommand(MoveCommand.Direction.LEFT), cmd);
	}

	@Test
	public void parseHandlesMoveRightCommand() throws ParserException {
		ICommand cmd = CommandParser.parse("r");
		assertEquals(new MoveCommand(MoveCommand.Direction.RIGHT), cmd);
	}

	@Test
	public void parseHandlesWallCommand() throws ParserException {
		ICommand cmd = CommandParser.parse("wall 3 3 3 5");
		assertEquals(new WallCommand(new Point(3, 3), new Point(3, 5)), cmd);
	}

	// @Test(expected = ParserException.class)
	// public void parseThrowsExceptionOnWallCommandReferencingTilesOutsideOfGame {
	// }

	// @Test(expected = ParserException.class)
	// public void parseThrowsExceptionOnWallCommandWithIdenticalCoordinates {
	// }

	// @Test(expected = ParserException.class)
	// public void parseThrowsExceptionOnWallCommandWithTooFarApartCoordinates {
	// }

	// @Test(expected = ParserException.class)
	// public void parseThrowsExceptionOnWallCommandWithDiagonalCoordinates {
	// }


	@Test(expected = ParserException.class)
	public void parseThrowsParserExceptionOnInvalidCommand() throws ParserException {
		CommandParser.parse("udlr");
	}
}
