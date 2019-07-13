package ninja.cooperstuff.pokemon.monster;

import ninja.cooperstuff.pokemon.util.Direction;
import ninja.cooperstuff.pokemon.util.ResourceLoader;
import ninja.cooperstuff.pokemon.util.Stats;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;

public class Monster {
	public static HashSet<Monster> monsters = new HashSet<>();
	public static ArrayList<Monster> ids = new ArrayList<>();

	static {
		Monster.ids.add(null);
	}

	public String name;
	public Stats baseStats;
	private BufferedImage image;
	public SpriteLayout spriteLayout;

	public Monster(String name) {
		this.name = name;
		this.image = ResourceLoader.load(String.format("pokemon/sprites/%s.png", this.name.toLowerCase()));
		this.spriteLayout = new SpriteLayout(this.image);
		Monster.monsters.add(this);
		Monster.ids.add(this);
	}

	public static class SpriteLayout {
		private static int size = 32;

		public BufferedImage[] up = new BufferedImage[2];
		public BufferedImage[] down = new BufferedImage[2];
		public BufferedImage[] left = new BufferedImage[2];
		public BufferedImage[] right = new BufferedImage[2];

		public SpriteLayout(BufferedImage sprite) {
			this.up[0] = sprite.getSubimage(0, 0, size, size);
			this.up[1] = sprite.getSubimage(size, 0, size, size);
			this.down[0] = sprite.getSubimage(0, size, size, size);
			this.down[1] = sprite.getSubimage(size, size, size, size);
			this.left[0] = sprite.getSubimage(0, 2*size, size, size);
			this.left[1] = sprite.getSubimage(size, 2*size, size, size);
			this.right[0] = sprite.getSubimage(0, 3*size, size, size);
			this.right[1] = sprite.getSubimage(size, 3*size, size, size);
		}

		public BufferedImage[] get(Direction dir) {
			switch (dir) {
				case UP:
					return this.up;
				case DOWN:
					return this.down;
				case LEFT:
					return this.left;
				default:
					return this.right;
			}
		}

		public BufferedImage get(Direction dir, int index) {
			BufferedImage[] bi = this.get(dir);
			return bi[index % bi.length];
		}
	}
}
