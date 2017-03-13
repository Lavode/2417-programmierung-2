package turtle;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;

import turtle.Parser;
import turtle.Command;

public class ParserTest
{
	@Test
	public void parsesNorthCommand() throws ParserException {
		List<Command> cmdList = Parser.parse("north 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					Parser.northCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesEastCommand() throws ParserException  {
		List<Command> cmdList = Parser.parse("east 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					Parser.eastCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesSouthCommand() throws ParserException  {
		List<Command> cmdList = Parser.parse("south 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					Parser.southCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesWestCommand() throws ParserException  {
		List<Command> cmdList = Parser.parse("west 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					Parser.westCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesListOfCommands() throws ParserException  {
		List<Command> cmdList = Parser.parse("north 1\nsouth 1\nwest 1\nnorth 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					Parser.northCommand(1),
					Parser.southCommand(1),
					Parser.westCommand(1),
					Parser.northCommand(1)
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesCommandsWithArbitraryLengthParameter() throws ParserException  {
		List<Command> cmdList = Parser.parse("north 3\nsouth 12");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					Parser.northCommand(3),
					Parser.southCommand(12)
				)
			),
			cmdList
		);
	}

	@Test(expected = ParserException.class)
	public void throwsParsereExceptionOnUnknownDirection() throws ParserException  {
		List<Command> cmdList = Parser.parse("giblbyboo 3");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnMissingNumber() throws ParserException  {
		List<Command> cmdList = Parser.parse("north\n");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnMissingDirection() throws ParserException  {
		List<Command> cmdList = Parser.parse("12\n");
	}

	@Test(expected = ParserException.class)
	public void throwsParserExceptionOnEmptyLine() throws ParserException  {
		List<Command> cmdList = Parser.parse("\n");
	}
}


