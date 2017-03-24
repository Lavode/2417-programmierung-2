package quoridor;

import java.awt.Point;

public class Player {
	private String name;
	private char sign;
	private Point position;
	private Target target;

	public enum Target {
		LEFT, RIGHT, UP, DOWN
	}

	public Player(String name, char sign, Point position, Target target) {
		this.name = name;
		this.sign = sign;
		this.position = position;
		this.target = target;
	}

	public String name() {
		return this.name;
	}
}
