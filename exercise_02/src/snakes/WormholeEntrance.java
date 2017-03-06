package snakes;

import java.util.Random;

public class WormholeEntrance extends Square {

	public WormholeEntrance(Game game, int position) {
		super(game, position);
	}
	
	public void teleport(){
		
	}

	@Override
	public boolean isWormholeEntrance(){
		return true;
	}
}

