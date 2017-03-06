package snakes;

public class SlowDownSquare extends Square {
	private int slowdownFactor = 2;
	public SlowDownSquare(Game game, int position) {
		super(game, position);
	}
	public ISquare moveAndLand(int moves) {
		if(moves <= 1)
		{
			return game.findSquare(position, moves).landHereOrGoHome();
		}
		else
		{
			return game.findSquare(position, moves / slowdownFactor).landHereOrGoHome();
		}
	}
}
