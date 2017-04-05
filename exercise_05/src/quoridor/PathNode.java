package quoridor;

import java.awt.Point;

public class PathNode
{
	private Point position;
	private boolean visited = false;
	private int distance = Integer.MAX_VALUE;
	private boolean hasWall;

	public PathNode(Point position, boolean hasWall) {
		this.position = position;
		this.hasWall = hasWall;
	}

	public boolean visited() {
		return this.visited;
	}

	public void visit() {
		this.visited = true;
	}

	public boolean hasWall() {
		return this.hasWall;
	}

	public int distance() {
		return this.distance;
	}

	public void setDistance(int d) {
		this.distance = d;
	}

	public Point position() {
		return this.position;
	}

	public String toString() {
		char vis = this.visited ? 'x' : 'o';
		char wl =  this.hasWall ? '#' : ' ';
		return String.format("(%2s,%2s), d: %10s, v: %s (%s)", this.position.x, this.position.y, this.distance, vis, wl);
	}
}
