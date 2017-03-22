package snakes;

import java.util.Queue;
import java.util.LinkedList;
import org.junit.*;
import snakes.squares.Square;
import snakes.squares.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class WormholeTest {
	
	@Test
	public void wormholeTest2Exits()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(5, players, 6);
		game.setSquare(2, new WormholeEntranceSquare(game, 2));
		game.setSquare(3, new WormholeExitSquare(game, 3));
		game.setSquare(4, new WormholeExitSquare(game, 4));
		
		game.movePlayer(1);
		assertTrue(player1.position() == 3 || player1.position() == 4);
	}
	
	@Test
	public void wormholeTestNoExit()
	{
		Player player1 = new Player("Marcel");
		Player player2 = new Player("Elias");
		Queue<Player> players = new LinkedList<Player>();
		players.add(player1);
		players.add(player2);
		Game game = new Game(5, players, 6);
		game.setSquare(2, new WormholeEntranceSquare(game, 2));
		
		game.movePlayer(1);
		assertTrue(player1.position() == 2);
	}
}
