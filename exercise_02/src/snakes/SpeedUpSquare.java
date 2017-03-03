package snakes;

public class SpeedUpSquare extends Square {
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}

	public String squareLabel() {
		return super.squareLabel() + " (SpeedUp)";
	}
}
