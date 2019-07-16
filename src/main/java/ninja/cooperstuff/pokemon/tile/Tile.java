package ninja.cooperstuff.pokemon.tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashSet;

public class Tile {
	public static HashSet<Tile> tiles = new HashSet<>();

	private String name;
	private String path;
	private BufferedImage sprite;

	public Tile(String name, String path) {
		this.name = name;
		this.path = path;
		try {
			this.sprite = ImageIO.read(this.getClass().getResourceAsStream(String.format("/pokemon/tiles/%s", this.path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Tile.tiles.add(this);
	}

	public BufferedImage getSprite() {
		return this.sprite;
	}
}
