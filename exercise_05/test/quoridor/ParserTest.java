package quoridor;

import java.awt.Point;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quoridor.Parser;

public class ParserTest
{
	@Test
	public void parseHandlesSimpleBoard() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\nGeorge G 3 4 D";
		Game game = Parser.parse(input);

		assertEquals(new Player("John", 'J', new Point(1, 1), Player.Target.RIGHT), game.players()[0]);
		assertEquals(new Player("George", 'G', new Point(3, 4), Player.Target.DOWN), game.players()[1]);
		assertEquals(7, game.width());
		assertEquals(9, game.height());
	}

	@Test
	public void parseHandlesPlayerNameConsistingOfMultipleWords() throws ParserException {
		String input = "7 9\nWill Smith J 1 1 R\nGeorge Orwell G 3 4 D";
		Game game = Parser.parse(input);

		assertEquals("Will Smith", game.players()[0].name());
		assertEquals("George Orwell", game.players()[1].name());
	}

	@Test
	public void parseHandlesMoreThanTwoPlayers() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\nGeorge G 3 4 D\nWilliam W 4 5 U\n";
		Game game = Parser.parse(input);

		assertEquals(new Player("John", 'J', new Point(1, 1), Player.Target.RIGHT), game.players()[0]);
		assertEquals(new Player("George", 'G', new Point(3, 4), Player.Target.DOWN), game.players()[1]);
		assertEquals(new Player("William", 'W', new Point(4, 5), Player.Target.UP), game.players()[2]);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfLessThanTwoPlayersListed() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\n";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfInvalidTargetEncountered() throws ParserException {
		String input = "7 9\nJohn J 1 1 X\nGeorge G 3 4 W";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnNonNumericCoordinates() throws ParserException {
		String input = "7 9\nJohn J 1 a R\nGeorge G 3 4 D";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnNonNumericBoardSize() throws ParserException {
		String input = "7 a\nJohn J 1 1 R\nGeorge G 3 4 D";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExcceptionOnIncompleteLineEncountered() throws ParserException {
		String input = "7 9\nJohn J 1 1 R\nGeorge G 3";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnEmptyInput() throws ParserException {
		String input = "";
		Parser.parse(input);
	}
}
