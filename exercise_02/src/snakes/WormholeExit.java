package snakes;

public class WormholeExit extends Square {

	public WormholeExit(Game game, int position) {
		super(game, position);
	}

	public String squareLabel() {
		return super.squareLabel() + " (Exit)";
	}
}
