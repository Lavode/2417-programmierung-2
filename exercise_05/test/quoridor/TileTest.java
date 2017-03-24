package quoridor;

import java.awt.Point;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quoridor.Tile;

public class TileTest
{
	private Tile tile = new Tile(1, 1);

	@Test
	public void equalsChecksForIdenticalCoordinates() {
		assertNotEquals(tile, new Tile(2, 1));
		assertNotEquals(tile, new Tile(1, 2));
	}

	@Test
	public void equalsReturnsTrueForIdenticalTiles() {
		assertEquals(tile, new Tile(1, 1));
	}
}
