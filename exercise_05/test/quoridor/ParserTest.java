package quoridor;

import java.awt.Point;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quoridor.Parser;

public class ParserTest
{
	@Test
	public void parseHandlesSimpleBoard() {
		String input = "7 9\nJohn J 1 1 R\nGeorge 3 4 D";
		Game game = Parser.parse(input);

		assertEquals(new Player("John", 'J', new Point(1, 1), Player.Target.RIGHT), game.player1());
		assertEquals(new Player("George", 'G', new Point(3, 4), Player.Target.DOWN), game.player2());
		assertEquals(7, game.width());
		assertEquals(9, game.height());
	}

	@Test
	public void parseHandlesPlayerNameConsistingOfMultipleWords() {
		String input = "7 9\nWill Smith J 1 1 R\nGeorge Orwell 3 4 D";
		Game game = Parser.parse(input);

		assertEquals("Will Smith", game.player1().name());
		assertEquals("George Orwell", game.player2().name());
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfMoreThanTwoPlayersListed() {
		String input = "7 9\nJohn J 1 1 R\nGeorge 3 4 D\nWiliam 4 5 U\n";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfLessThanTwoPlayersListed() {
		String input = "7 9\nJohn J 1 1 R\n";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionIfInvalidTargetEncountered() {
		String input = "7 9\nJohn J 1 1 X\nGeorge 3 4 D";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnNonNumericCoordinates() {
		String input = "7 9\nJohn J 1 a R\nGeorge 3 4 D";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExceptionOnNonNumericBoardSize() {
		String input = "7 a\nJohn J 1 1 R\nGeorge 3 4 D";
		Parser.parse(input);
	}

	@Test(expected = ParserException.class)
	public void parseThrowsExcceptionOnIncompleteLineEncountered() {
		String input = "7 9\nJohn J 1 1 R\nGeorge 3";
		Parser.parse(input);
	}
}
