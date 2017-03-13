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
	public void parsesNorthCommand() {
		List<Command> cmdList = Parser.parse("north 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					northCommand()
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesEastCommand() {
		List<Command> cmdList = Parser.parse("east 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					eastCommand()
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesSouthCommand() {
		List<Command> cmdList = Parser.parse("south 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					southCommand()
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesWestCommand() {
		List<Command> cmdList = Parser.parse("west 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					westCommand()
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesListOfCommands() {
		List<Command> cmdList = Parser.parse("north 1\nsouth 1\nwest 1\nnorth 1");

		assertEquals(
			new ArrayList<Command>(
				Arrays.asList(
					northCommand(),
					southCommand(),
					westCommand(),
					northCommand()
				)
			),
			cmdList
		);
	}

	@Test
	public void parsesCommandsWithArbitraryLengthParameter() {
	}

	@Test
	public void throwsParsereExceptionOnInvalidInput() {
	}

	private Command northCommand() {
		return new Command(Command.Direction.NORTH);
	}

	private Command eastCommand() {
		return new Command(Command.Direction.EAST);
	}

	private Command southCommand() {
		return new Command(Command.Direction.SOUTH);
	}

	private Command westCommand() {
		return new Command(Command.Direction.WEST);
	}
}


