/* Special Square
 * 
 * Provides following functionality:
 * - If a player lands on a WormholeEntrance Square, he will automatically be moved to a WormholeExit square
 * 
 */

package snakes;

public class WormholeExit extends Square {

	public WormholeExit(Game game, int position) {
		super(game, position);
	}

	@Override
	public boolean isWormholeExit(){
		return true;
	}

	public String squareLabel() {
		return super.squareLabel() + " (Exit)";
	}
}

