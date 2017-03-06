package snakes;

public class SpeedUpSquare extends Square {
	private int speedFactor = 2;
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}
	public ISquare moveAndLand(int moves) {
		return game.findSquare(position, moves * speedFactor).landHereOrGoHome();
	}
}