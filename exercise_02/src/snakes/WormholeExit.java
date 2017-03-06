package snakes;

public class WormholeExit extends Square {

	private static int count = 0;
	private int index;
	public WormholeExit(Game game, int position) {
		super(game, position);
		count++;
		index = count;
	}

	@Override
	public boolean isWormholeExit() {
		return true;
	}
	public static int getCounter(){
		return count;
	}
	public int getIndex(){
		return index;
	}
}