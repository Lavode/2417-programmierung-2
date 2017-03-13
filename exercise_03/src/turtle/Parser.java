package turtle;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class Parser
{
	static Pattern COMMAND_PATTERN = Pattern.compile("^(north|east|south|west) ([0-9]+)$");

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
			}
		}

		throw new ParserException();
	}

	private static Command northCommand(int count) {
		return new Command(Command.Direction.NORTH, count);
	}

	private static Command eastCommand(int count) {
		return new Command(Command.Direction.EAST, count);
	}

	private static Command southCommand(int count) {
		return new Command(Command.Direction.SOUTH, count);
	}

	private static Command westCommand(int count) {
		return new Command(Command.Direction.WEST, count);
	}
}
