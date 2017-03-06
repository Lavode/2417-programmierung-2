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

<<<<<<< HEAD
=======
	public String squareLabel() {
		return super.squareLabel() + " (Entrance)";
	}
}
>>>>>>> fafddb92fdbf678022f3642fa7bf2c90d2adff9f
