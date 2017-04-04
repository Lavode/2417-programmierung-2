package quoridor;

public interface ICommand
{
	/* TODO: There must be a better way to allow implementations to throw their own exceptions? */
	public void execute(Player player) throws TileOccupiedException;
}
