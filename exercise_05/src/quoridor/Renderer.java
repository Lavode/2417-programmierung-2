package quoridor;

import java.util.*;

/**
 * Renders a {@link Game} object.
 */
public class Renderer {
	
	public void renderBoard(int width, int height, Player[] paricipants)
	{
		/*place Walls (#) around field and put Player Symbol on according positions*/
		for(int x = 0; x <= width + 1; x++)
		{
			for(int y = 0; y <= height + 1; y++)
			{
				if(x == 0 || y == 0 || x == width + 1 || y == height + 1)
				{
					System.out.print('#');
				}
				
				else
				{
					
				}
			}
		}
	}
}