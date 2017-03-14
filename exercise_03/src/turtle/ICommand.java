package turtle;

public interface ICommand
{
	public void execute(BoardMaker board);

	public boolean equals(Object other);
}
