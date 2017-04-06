package quoridor;

/**
 * Interface to be implemented by classes representing a player's command.
 */
public interface ICommand
{
	/* TODO: There must be a better way to allow implementations to throw their own exceptions? */
	/**
	 * Execute command onto given player.
	 *
	 * May call various methods of the player's API to e.g build walls,
	 * move around etc.
	 *
	 * @param player Player which to execute command on.
	 *
	 * @throws TileOccupiedException If moving onto an occupied tile.
	 */
	public void execute(Player player) throws TileOccupiedException;
}
