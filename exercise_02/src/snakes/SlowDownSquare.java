package snakes;

public class SlowDownSquare extends Square {
	private int slowdownFactor = 2;
	public SlowDownSquare(Game game, int position) {
		super(game, position);
	}
<<<<<<< HEAD
	public ISquare moveAndLand(int moves) {
		if(moves <= 1)
		{
			return game.findSquare(position, moves).landHereOrGoHome();
		}
		else
		{
			return game.findSquare(position, moves / slowdownFactor).landHereOrGoHome();
		}
=======

	public String squareLabel() {
		return super.squareLabel() + " (SlowDown)";
	}

	@Override
	public ISquare moveAndLand(int moves) {
		int halfedMoves = moves / 2;
		if (halfedMoves == 0) {
			halfedMoves = 1;
		}

		return super.moveAndLand(halfedMoves);
>>>>>>> fafddb92fdbf678022f3642fa7bf2c90d2adff9f
	}
}
