package snakes;

public class SpeedUpSquare extends Square {
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}

	public String squareLabel() {
		return super.squareLabel() + " (SpeedUp)";
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return super.moveAndLand(moves * 2);
	}
}
