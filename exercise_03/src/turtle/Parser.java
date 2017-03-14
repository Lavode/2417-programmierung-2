package turtle;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class Parser
{
	static Pattern COMMAND_PATTERN = Pattern.compile("^(north|east|south|west|north east|north west|south east|south west) ([0-9]+)$");

	public static List<Command> parse(String program) throws ParserException {
		List<Command> cmdList = new ArrayList<Command>();

		Scanner scanner = new Scanner(program);

		while (scanner.hasNextLine()) {
			String cmd = scanner.nextLine();
			cmdList.add(parseCommand(cmd));
		}

		return cmdList;
	}

	private static Command parseCommand(String cmd) throws ParserException {
		Matcher matcher = COMMAND_PATTERN.matcher(cmd);
		if (matcher.matches()) {
			String cmdDirection = matcher.group(1);
			int cmdCount = Integer.parseInt(matcher.group(2));

			switch(cmdDirection) {
				case "north":
					return northCommand(cmdCount);
				case "east":
					return eastCommand(cmdCount);
				case "south":
					return southCommand(cmdCount);
				case "west":
					return westCommand(cmdCount);
				case "north east":
					return northEastCommand(cmdCount);
				case "north west":
					return northWestCommand(cmdCount);
				case "south east":
					return southEastCommand(cmdCount);
				case "south west":
					return southWestCommand(cmdCount);
			}
		}

		throw new ParserException();
	}

	protected static Command northCommand(int count) {
		return new Command(Command.Direction.NORTH, count);
	}

	protected static Command eastCommand(int count) {
		return new Command(Command.Direction.EAST, count);
	}

	protected static Command southCommand(int count) {
		return new Command(Command.Direction.SOUTH, count);
	}

	protected static Command westCommand(int count) {
		return new Command(Command.Direction.WEST, count);
	}

	protected static Command northEastCommand(int count) {
		return new Command(Command.Direction.NORTH_EAST, count);
	}

	protected static Command northWestCommand(int count) {
		return new Command(Command.Direction.NORTH_WEST, count);
	}

	protected static Command southEastCommand(int count) {
		return new Command(Command.Direction.SOUTH_EAST, count);
	}

	protected static Command southWestCommand(int count) {
		return new Command(Command.Direction.SOUTH_WEST, count);
	}
}
