package ninja.cooperstuff.pokemon.tile;

import ninja.cooperstuff.engine.util.IntVector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;

public class Tile {
	public static HashSet<Tile> tiles = new HashSet<>();

	protected String name;
	protected String path;
	protected BufferedImage sprite;
	protected boolean walkable;
	protected IntVector collisionCorner1 = new IntVector(0, 0);
	protected IntVector collisionCorner2 = new IntVector(16, 16);

	public Tile(String name, String path, boolean walkable) {
		this.name = name;
		this.path = path;
		this.walkable = walkable;
		try {
			this.sprite = ImageIO.read(this.getClass().getResourceAsStream(String.format("/pokemon/tiles/%s", this.path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Tile.tiles.add(this);
	}

	public Tile(String name, boolean walkable) {
		this.name = name;
		this.walkable = walkable;
	}

	public Tile setName(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public Tile setSprite(BufferedImage sprite) {
		this.sprite = sprite;
		return this;
	}

	public BufferedImage getSprite() {
		return this.sprite;
	}

	public Tile setCollisionCorner1(int x, int y) {
		this.collisionCorner1 = new IntVector(x, y);
		return this;
	}

	public IntVector getCollisionCorner1() {
		return this.collisionCorner1;
	}

	public Tile setCollisionCorner2(int x, int y) {
		this.collisionCorner2 = new IntVector(x, y);
		return this;
	}

	public IntVector getCollisionCorner2() {
		return this.collisionCorner2;
	}

	public boolean getWalkable() {
		return this.walkable;
	}
}
