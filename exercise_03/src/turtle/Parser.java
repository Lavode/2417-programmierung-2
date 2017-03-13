package turtle;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

public class Parser
{
	static Pattern MOVE_NORTH = Pattern.compile("^north ([0-9]+)$");
	static Pattern MOVE_EAST  = Pattern.compile("^east ([0-9]+)$");
	static Pattern MOVE_SOUTH = Pattern.compile("^south ([0-9]+)$");
	static Pattern MOVE_WEST  = Pattern.compile("^west ([0-9]+)$");

	public static List<Command> parse(String program) {
		List<Command> cmdList = new ArrayList<Command>();

		Scanner scanner = new Scanner(program);

		while (scanner.hasNextLine()) {
			String cmd = scanner.nextLine();

			if (MOVE_NORTH.matcher(cmd).matches()) {
				cmdList.add(northCommand());

			} else if (MOVE_EAST.matcher(cmd).matches()) {
				cmdList.add(eastCommand());

			} else if (MOVE_SOUTH.matcher(cmd).matches()) {
				cmdList.add(southCommand());

			} else if (MOVE_WEST.matcher(cmd).matches()) {
				cmdList.add(westCommand());
			}
		}

		return cmdList;
	}

	private static Command northCommand() {
		return new Command(Command.Direction.NORTH);
	}

	private static Command eastCommand() {
		return new Command(Command.Direction.EAST);
	}

	private static Command southCommand() {
		return new Command(Command.Direction.SOUTH);
	}

	private static Command westCommand() {
		return new Command(Command.Direction.WEST);
	}
}
