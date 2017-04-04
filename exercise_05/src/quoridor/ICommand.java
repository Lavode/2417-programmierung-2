package quoridor;

public interface ICommand
{
	/* TODO: There must be a better way to allow implementations to throw an exception? */
	public void execute(Player player) throws Exception;
}
