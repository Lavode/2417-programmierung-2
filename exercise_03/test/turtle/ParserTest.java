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
					northCommand(1)
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
					eastCommand(1)
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
					southCommand(1)
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
					westCommand(1)
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
					northCommand(1),
					southCommand(1),
					westCommand(1),
					northCommand(1)
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
					northCommand(3),
					southCommand(12)
				)
			),
			cmdList
		);
	}

	@Test(expected = ParserException.class)
	public void throwsParsereExceptionOnInvalidInput() throws ParserException  {
		List<Command> cmdList = Parser.parse("giblbyboo 3\nsouth 12");
	}

	private Command northCommand(int count) {
		return new Command(Command.Direction.NORTH, count);
	}

	private Command eastCommand(int count) {
		return new Command(Command.Direction.EAST, count);
	}

	private Command southCommand(int count) {
		return new Command(Command.Direction.SOUTH, count);
	}

	private Command westCommand(int count) {
		return new Command(Command.Direction.WEST, count);
	}
}


