package snakes;

import java.util.Random;

public class WormholeEntrance extends Square {

	public WormholeEntrance(Game game, int position) {
		super(game, position);
	}
	
	@Override
	public ISquare landHereOrGoHome() {
		return this.destination().landHereOrGoHome();
	}

	protected ISquare destination() {
		Random generator = new Random();
		int length = this.game.wormholeExits().size();
		int listPosition = generator.nextInt(length);
		return game.wormholeExits().get(listPosition);
	}
	
	public String squareLabel() {
		return super.squareLabel() + " (Entrance)";
	}
}
