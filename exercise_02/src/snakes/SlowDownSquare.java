package snakes;

public class SlowDownSquare extends Square {
	public SlowDownSquare(Game game, int position) {
		super(game, position);
	}

	public String squareLabel() {
		return super.squareLabel() + " (SlowDown)";
	}
}
