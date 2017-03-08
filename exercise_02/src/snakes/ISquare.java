package snakes;

/**
 * An interface to be implemented by classes which are to be used as squares in the 'Snakes and Ladders' game.
 * 
 * @author Pascal Gerig 
 * @author Michael Senn
 *
 */
public interface ISquare {
	/**
	 * @return Position of this square on the field, starting at 1.
	 */
	public int position();

	/**
	 * @param moves Number of fields to advance
	 * @return Square which to land on after having moved `moves` squares.
	 */
	public ISquare moveAndLand(int moves);

	/**
	 * @return Whether this square is the first square.
	 */
	public boolean isFirstSquare();

	/**
	 * @return Whether this square is the last square.
	 */
	public boolean isLastSquare();

	/**
	 * Callback when player enters square.
	 * 
	 * @param player Player object which entered the square.
	 */
	public void enter(Player player);

	/**
	 * Callback when player leaves square.
	 * 
	 * @param player Player object which left the square.
	 */
	public void leave(Player player);

	/**
	 * @return Whether the square is occupied.
	 */
	public boolean isOccupied();

	/**
	 * Method to allow square to override destination square of incoming player.
	 * 
	 * @return Square which arriving player should land on.
	 */
	public ISquare landHereOrGoHome();

	/**
	 * @return Whether this square is a wormhole exit.
	 */
	public boolean isWormholeExit();
}
