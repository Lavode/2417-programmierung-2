/* Special Square
 * 
 * Provides following functionality:
 * - Override on moveAndLand method, Players move only goes half as far as normal
 * 
 */
package snakes;

public class SlowDownSquare extends Square {
	private final int slowDownFactor = 2;
	public SlowDownSquare(Game game, int position) {
		super(game, position);
	}
	public String squareLabel() {
		return super.squareLabel() + " (SlowDown)";
	}

	@Override
	public ISquare moveAndLand(int moves) {
		int halfedMoves = moves / slowDownFactor;
		if (halfedMoves == 0) {
			halfedMoves = 1;
		}

		return super.moveAndLand(halfedMoves);
	}
}
