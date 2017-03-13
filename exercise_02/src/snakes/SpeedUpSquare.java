/* Special Square
 * 
 * Provides following functionality:
 * - Override on moveAndLand method, Players move goes double as far as normal
 * 
 */

package snakes;

public class SpeedUpSquare extends Square {
	private final int SPEED_UP_FACTOR = 2;
	public SpeedUpSquare(Game game, int position) {
		super(game, position);
	}

	public String squareLabel() {
		return super.squareLabel() + " (SpeedUp)";
	}

	@Override
	public ISquare moveAndLand(int moves) {
		return super.moveAndLand(moves * SPEED_UP_FACTOR);
	}
}
