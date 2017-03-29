package quoridor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RendererTest
{
	private Game game;
	private Renderer renderer;

	@Before
	public void init() {
		game = new Game();
		game.setDimension(5, 3);
		game.addPlayer(new Player("Michael", 'M', game.getTile(2, 1), Player.Target.UP));
		game.addPlayer(new Player("Pascal", 'P', game.getTile(5, 3), Player.Target.DOWN));

		renderer = new Renderer(game);
	}

	@Test
	public void renderReturnsTextRenderingOfGameObject() {
		String should = "#######\n# M   #\n#     #\n#    P#\n#######\n";

		assertEquals(should, renderer.render());
	}
}
