package quoridor;

import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a Player of the quoridor game.
 * Can move by one step eighter up, down, left or right.
 * 
 * @author Pascal Gerig
 * @author Michael Senn
 *
 */
public class Player {
	private static final int WALLS_PER_PLAYER = 5;

	private Game game;
	private final String name;
	private final char sign;

	private int wallsAvailable = WALLS_PER_PLAYER;

	private Tile tile;
	private Target target;

	public enum Target {
		LEFT, RIGHT, UP, DOWN
	}

	public enum CommandType {
		MOVEMENT, PLACEWALL
	}

	public Player(String name, char sign, Tile tile, Target target) {
		assert !tile.isOccupied();
		this.name = name;
		this.sign = sign;
		this.tile = tile;
		this.tile.enter(this);
		this.target = target;
		this.game = null;
	}

	/**
	 * @return true if player is in a game, false otherwise
	 */
	public boolean hasGame()
	{
		return this.game != null;
	}

	/**
	 * lets a player enter a game.
	 * 
	 * @param game which player should enter
	 */
	public void enterGame(Game game)
	{
		this.game = game;
	}

	/**
	 * @return Players name
	 */
	public String name() {
		return this.name;
	}

	/**
	 * @return Players sign
	 */
	public char sign() {
		return this.sign;
	}

	/**
	 * @return Tile on which Player is currently standing
	 */
	public Tile currentPosition() {
		return this.tile;
	}

	/**
	 * @return Players target
	 */
	public Target target() {
		return this.target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	/**
	 * @return Point object of Players current Position
	 */
	public Point position() {
		return this.tile.position();
	}

	/**
	 * Checks two objects for equality
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Player)) {
			return false;
		}

		Player player = (Player)other;

		return (
				player.name().equals(this.name) &&
				player.sign() == this.sign &&
				player.currentPosition().equals(this.tile) &&
				player.target() == this.target
		);
	}

	/**
	 * @return String representation of player object, suitable for
	 * consumption by humans.
	 */
	public String toString() {
		return String.format("<%s> [%s] @ (%s, %s) -> %s", this.name, this.sign, this.tile.position().x, this.tile.position().y, this.target);
	}

	/**
	 * Checks if there is still a path for the player so he can win the game after
	 * a potentially new Wall has been placed between (xFrom, yFrom) and (xTo, yTo).
	 * @param xFrom x-Coordinate
	 * @param yFrom y-Coordinate
	 * @param xTo x-Coordinate
	 * @param yTo y-Coordinate
	 * @return
	 */
	private boolean hasPath(int xFrom, int yFrom, int xTo, int yTo)
	{
		//TODO Check if there is still a Winning path after a Wall has been placed
		return false;
	}

	/**
	 * Lets Player place a Wall between (xFrom, yFrom) and (xTo, yTo)
	 */
	public void placeWall(Point from, Point to) throws TileOccupiedException {
		assert(this.wallsAvailable > 0);
		game.buildWall(from, to);
		this.wallsAvailable--;
	}

	public void jump(int x, int y) throws TileOccupiedException {
		moveTo(this.game.getTile(x, y));

		assert(invariant());
	}

	/**
	 * Tries to move the player up by one field
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveUp() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x, this.position().y - 1));

		assert(invariant());
	}

	/**
	 * Tries to move the player down by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveDown() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x, this.position().y + 1));

		assert(invariant());
	}

	/**
	 * tries to move the player to the right by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveRight() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x + 1, this.position().y));

		assert(invariant());
	}

	/**
	 * tries to move the player to the left by one tile
	 * @throws TileOccupiedException in case of other player beeing on Tile the player tries to land on
	 */
	public void moveLeft() throws TileOccupiedException {
		moveTo(this.game.getTile(this.position().x - 1, this.position().y));

		assert(invariant());
	}

	private void moveTo(Tile next) throws TileOccupiedException {
		/* TODO: Throwing a TileOccupiedException should happen within
		 * the tile - it has the authority to decide whether another
		 * player fits or not.  However, care must be taken not to end
		 * up with a ghost player sitting on a tile.
		 */
		if (next.isOccupied() || next.hasWall()) {
			throw new TileOccupiedException("Field you want to move to is occupied!");
		} else {
			this.tile.leave();
			next.enter(this);
			this.tile = next;
		}
	}

	private boolean invariant() {
		return this.tile != null;
	}

	/**
	 * checks if player is at Target
	 */
	public boolean hasFinished() {
		if(this.target() == Player.Target.DOWN)
			return this.tile.position().y == game.height();
		else if(this.target() == Player.Target.UP)
			return this.tile.position().y == 1;
		else if(this.target() == Player.Target.LEFT)
			return this.tile.position().x == 1;
		else
			return this.tile.position().x == game.width();
	}

	public boolean canReachTarget() {
		System.out.println(this.toString());
		for (Tile targetTile : targetTiles()) {
			// System.out.println(t.toString());
			PathFinding path = new PathFinding(this.game.toPathFindingBoard(), this.position(), targetTile.position());
			if (path.existsPath()) {
				/* Path to one target square found. */
				return true;
			}
		}
		/* No target square reachable. */
		return false;
	}

	private List<Tile> targetTiles() {
		List<Tile> out = new ArrayList<Tile>();

		switch (this.target) {
			case UP:
				for (int i = 1; i <= this.game.width(); i++) {
					out.add(this.game.getTile(i, 1));
				}
				break;
			case DOWN:
				for (int i = 1; i <= this.game.width(); i++) {
					out.add(this.game.getTile(i, this.game.height()));
				}
				break;
			case LEFT:
				for (int i = 1; i <= this.game.height(); i++) {
					out.add(this.game.getTile(1, i));
				}
				break;
			case RIGHT:
				for (int i = 1; i <= this.game.height(); i++) {
					out.add(this.game.getTile(this.game.width(), i));
				}
				break;
		}

		for (Iterator<Tile> iterator = out.iterator(); iterator.hasNext();) {
			Tile tile = iterator.next();
			if (tile.hasWall()) {
				iterator.remove();
			}
		}

		return out;
	}
}
