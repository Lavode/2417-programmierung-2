package snakes;

public class SpeedUpSquare extends Square {
	private int speedFactor = 2;
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}
<<<<<<< HEAD
	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves * speedFactor).landHereOrGoHome();
	}
}
=======

	public String squareLabel() {
		return super.squareLabel() + " (SpeedUp)";
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return super.moveAndLand(moves * 2);
	}
}
>>>>>>> fafddb92fdbf678022f3642fa7bf2c90d2adff9f
